package com.webapp.bankapp.dao;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
import com.webapp.bankapp.utils.DAOFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webapp.bankapp.models.Account;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BranchDAOTest {
    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private DAOFactory daoFactory;

    @Test
    public void testGetAll() {
        List<Branch> list;
        list =  DAOFactory.getInstance().getBranchDAO().getAll();
        assertNotNull(list);
        assertEquals(list.size(), 4);
    }

    @Test
    public void testGetById() {
        Branch b = DAOFactory.getInstance().getBranchDAO().getById(3);
        assertNotNull(b);
        assertEquals(b.getName(), "Павелецкое отделение");

        b = DAOFactory.getInstance().getBranchDAO().getById(42);
        assertNull(b);
    }

    @Test
    public void testGetByStringFilter() {
        String filter = "Садовая";
        List<Branch> list = DAOFactory.getInstance().getBranchDAO().getByStringFilter(filter);
        assertNotNull(list);
        assertEquals(list.size(), 1);

        filter = "отделение 404";
        list = DAOFactory.getInstance().getBranchDAO().getByStringFilter(filter);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }
}
