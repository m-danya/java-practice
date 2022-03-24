package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.AccountDAO;
import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.AccountType;
import com.webapp.bankapp.utils.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.List;

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
    public List<Account> find_by_type(AccountType type) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Account> query = session.createQuery
                ("from Account where type = :type_param", Account.class)
                .setParameter("type_param", type);
        List<Account> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Account> find_by_balance_range(BigInteger from, BigInteger to) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Account> query = session.createQuery
                ("from Account where balance >= :param_a and balance <= :param_b", Account.class)
                .setParameter("param_a", from).setParameter("param_b", to);
        List<Account> result = query.getResultList();
        session.close();
        return result;
    }
}
