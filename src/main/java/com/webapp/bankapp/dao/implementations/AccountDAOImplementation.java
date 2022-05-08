package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.AccountDAO;
import com.webapp.bankapp.models.*;
import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.utils.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Repository
public class AccountDAOImplementation implements AccountDAO {

    @Override
    public void add(Account account) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Account account) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(account);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(Account account) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(account);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Account> getAll() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Account> query = session.createQuery
                ("from Account");
        List<Account> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Account> getByType(Integer type) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                        ("from Account as account inner join account.type as type " +
                                " where type.id = :type_param")
                .setParameter("type_param", type);
        List<Object[]> result = query.list();
        List<Account> accounts = new ArrayList<Account>();
        if (result.size() == 0) {
            session.close();
            return accounts;
        }
        for (Object[] row : result) {
            accounts.add((Account) row[0]);
        }
        session.close();
        return accounts;
    }

    @Override
    public List<Account> getByBalanceRange(BigInteger from, BigInteger to) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Account> query = session.createQuery
                        ("from Account where balance >= :param_a and balance <= :param_b", Account.class)
                .setParameter("param_a", from).setParameter("param_b", to);
        List<Account> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Account> getByClient(Client client) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery(
                "from Account as account" +
                        " inner join account.client as client" +
                        " where client = :client"
        );
        query.setParameter("client", client);
        List<Object[]> result = query.list();
        List<Account> accounts = new ArrayList<Account>();
        if (result.size() == 0) {
            session.close();
            return accounts;
        }
        for (Object[] row : result) {
            accounts.add((Account) row[0]);
        }
        session.close();
        return accounts;
    }

    @Override
    public List<Account> getByBranch(Branch branch) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery(
                "from Account as account" +
                        " inner join account.branch as branch" +
                        " where branch = :branch"
        );
        query.setParameter("branch", branch);
        List<Object[]> result = query.list();
        List<Account> accounts = new ArrayList<Account>();
        if (result.size() == 0) {
            session.close();
            return accounts;
        }
        for (Object[] row : result) {
            accounts.add((Account) row[0]);
        }
        session.close();
        return accounts;
    }

    @Override
    public List<Account> getByOperationsDate(Date from, Date to) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                ("from Operation as operation " +
                        " inner join operation.account as account" +
                        " where operation.timestamp >= :from" +
                        " and operation.timestamp <= :to");
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Account[]> result = query.list();
        List<Account> accounts = new ArrayList<Account>();
        if (result.size() == 0) {
            session.close();
            return accounts;
        }
        for (Object[] row : result) {
            accounts.add((Account) row[1]);
        }
        session.close();
        return accounts;
    }


    @Override
    public List<Account> getByStringFilter(String filter) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                ("from Account as account" +
                        " where cast(account.balance as string) like :filter" +
                        " or cast(account.is_active as string) like :filter");
        query.setParameter("filter", "%" + filter + "%");
        List<Account> accounts = query.getResultList();
        session.close();
        return accounts;
    }

    @Override
    public Account getById(Integer id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Account> query = session.createQuery
                ("from Account as account where account.id = :id");
        query.setParameter("id", id);
        Account result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        session.close();
        return result;
    }

    @Override
    public AccountType getAccountType(Integer type) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<AccountType> query = session.createQuery
                ("from AccountType as type where type.id = :type");
        query.setParameter("type", type);
        AccountType result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        session.close();
        return result;
    }


}
