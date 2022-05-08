package com.webapp.bankapp.dao.implementations;

import com.webapp.bankapp.dao.BranchDAO;
import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
import com.webapp.bankapp.utils.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
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

    @Override
    public List<Branch> getAll() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Branch> query = session.createQuery
                ("from Branch", Branch.class);
        List<Branch> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Branch getById(Integer id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Branch> query = session.createQuery
                ("from Branch as branch where branch.id = :id");
        query.setParameter("id", id);
        Branch result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        session.close();
        return result;
    }

    @Override
    public List<Branch> getByStringFilter(String filter) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query query = session.createQuery
                ("from Branch as branch" +
                        " where cast(branch.name as string) like :filter" +
                        " or cast(branch.address as string) like :filter");
        query.setParameter("filter", "%" + filter + "%");
        List<Branch> branches = query.getResultList();
        session.close();
        return branches;
    }
}
