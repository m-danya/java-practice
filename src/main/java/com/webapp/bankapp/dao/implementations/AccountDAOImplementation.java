package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.AccountDAO;
import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.utils.HibernateUtility;
import org.hibernate.Session;

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
}
