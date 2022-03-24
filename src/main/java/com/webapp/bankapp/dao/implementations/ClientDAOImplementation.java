package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.ClientDAO;
import com.webapp.bankapp.models.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.webapp.bankapp.utils.HibernateUtility;

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
}
