package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.ClientDAO;
import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.webapp.bankapp.utils.HibernateUtility;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Repository
public class ClientDAOImplementation implements ClientDAO {
    @Override
    public void add(Client client) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Client client) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(Client client) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public Client getById(Integer id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Client> query = session.createQuery
                ("from Client as client where client.id = :id");
        query.setParameter("id", id);
        Client result;
        try {
            result = query.getSingleResult();
        } catch(NoResultException e) {
            result = null;
        }
        session.close();
        return result;
    }

    @Override
    public List<Client> getAll() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Client> query = session.createQuery
                        ("from Client", Client.class);
        List<Client> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Client> getByAccountType(Integer typeBeingSearchedFor) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                ("from Account as account inner join account.client as client" +
                        " inner join account.type as type where type.id = :type_number");
        query.setParameter("type_number", typeBeingSearchedFor);
        List<Object[]> result = query.list();
        List<Client> clients = new ArrayList<Client>();
        if (result.size() == 0) {
            session.close();
            return clients;
        }
        for(Object[] row : result) {
            clients.add((Client) row[1]);
        }
        session.close();
        return clients;
    }

    @Override
    public List<Client> getByOperationsDate(Date from, Date to) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                ("from Operation as operation inner join operation.account as account" +
                        " inner join account.client as client where operation.timestamp >= :from" +
                        " and operation.timestamp <= :to");
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Object[]> result = query.list();
        List<Client> clients = new ArrayList<Client>();
        if (result.size() == 0) {
            session.close();
            return clients;
        }
        for(Object[] row : result) {
            clients.add((Client) row[2]);
        }
        session.close();
        return clients;
    }
    @Override
    public List<Client> getByTotalBalanceRange(BigInteger from, BigInteger to) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select client.id from Account as account" +
                        " inner join account.client as client " +
                        " group by client.id" +
                        " having sum(account.balance) >= :from and" +
                        " sum(account.balance) <= :to");
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Integer> result = query.list();
        List<Client> clients = new ArrayList<Client>();
        if (result.size() == 0) {
            session.close();
            return clients;
        }
        for(Integer row : result) {
            clients.add(this.getById(row));
        }
        session.close();
        return clients;
    }

    @Override
    public List<Client> getByStringFilter(String filter) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                ("from Client as client" +
                        " where cast(client.name as string) like :filter" +
                        " or cast(client.surname as string) like :filter" +
                        " or cast(client.middle_name as string) like :filter" +
                        " or cast(client.address as string) like :filter" +
                        " or cast(client.phone_number as string) like :filter" +
                        " or cast(client.email as string) like :filter" +
                        " or cast(client.date_of_birth as string) like :filter");
        query.setParameter("filter", "%" + filter + "%");
        List<Client> clients = query.getResultList();
        session.close();
        return clients;
    }

    @Override
    public List<Client> getByBranch(Branch branch) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery(
                "from Account as account" +
                        " inner join account.branch as branch" +
                        " inner join account.client as client" +
                        " where branch = :branch"
        );
        query.setParameter("branch", branch);
        List<Object[]> result = query.list();
        List<Client> clients = new ArrayList<Client>();
        if (result.size() == 0) {
            session.close();
            return clients;
        }
        for(Object[] row : result) {
            clients.add((Client) row[2]);
        }
        session.close();
        return clients;
    }

}
