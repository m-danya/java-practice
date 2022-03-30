package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.OperationDAO;
import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
import com.webapp.bankapp.models.Operation;
import com.webapp.bankapp.utils.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationDAOImplementation implements OperationDAO {
    @Override
    public void add(Operation operation) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(operation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Operation operation) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(operation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(Operation operation) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(operation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Operation> getAll() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Operation> query = session.createQuery
                ("from Operation");
        List<Operation> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Operation getById(Integer id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Operation> query = session.createQuery
                ("from Operation as operation where operation.id = :id");
        query.setParameter("id", id);
        Operation result;
        try {
            result = query.getSingleResult();
        } catch(NoResultException e) {
            result = null;
        }
        session.close();
        return result;
    }

    @Override
    public List<Operation> getByAccount(Account account) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery("from Operation as operation" +
                        " inner join operation.account as account" +
                        " where account = :account"
        );
        query.setParameter("account", account);
        List<Object[]> result = query.list();
        List<Operation> operations = new ArrayList<Operation>();
        if (result.size() == 0) {
            session.close();
            return operations;
        }
        for(Object[] row : result) {
            operations.add((Operation) row[0]);
        }
        session.close();
        return operations;
    }
}
