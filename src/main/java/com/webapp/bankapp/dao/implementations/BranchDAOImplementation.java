package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.BranchDAO;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.utils.HibernateUtility;
import org.hibernate.Session;

public class BranchDAOImplementation implements BranchDAO {
    @Override
    public void add(Branch branch) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(branch);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Branch branch) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(branch);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(Branch branch) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(branch);
        session.getTransaction().commit();
        session.close();
    }
}
