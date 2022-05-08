package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
import com.webapp.bankapp.utils.DAOFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webapp.bankapp.models.Operation;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OperationDAOTest {
    @Autowired
    private OperationDAO operationDAO;

    @Autowired
    private DAOFactory daoFactory;

    @Test
    public void testGetAll() {
        List<Operation> list;
        list = DAOFactory.getInstance().getOperationDAO().getAll();
        assertNotNull(list);
        assertEquals(list.size(), 27);
    }

    @Test
    public void testGetById() {
        Operation b = DAOFactory.getInstance().getOperationDAO().getById(3);
        assertNotNull(b);
        assertEquals(b.getAmount(), BigInteger.valueOf(403129));

        b = DAOFactory.getInstance().getOperationDAO().getById(42);
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

    @Test
    public void testGetByAccount() {
        Account a = DAOFactory.getInstance().getAccountDAO().getById(4);
        List<Operation> list = DAOFactory.getInstance().getOperationDAO().getByAccount(a);
        assertNotNull(list);
        assertEquals(list.size(), 1);

        a = DAOFactory.getInstance().getAccountDAO().getById(16);
        list = DAOFactory.getInstance().getOperationDAO().getByAccount(a);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

}
