package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.OperationDAO;
import com.webapp.bankapp.models.Operation;
import com.webapp.bankapp.utils.HibernateUtility;
import org.hibernate.Session;

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
}
