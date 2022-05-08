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
public class AccountDAOTest {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private DAOFactory daoFactory;

    @Test
    public void testGetAll() {
        List<Account> list;
        list = DAOFactory.getInstance().getAccountDAO().getAll();
        assertNotNull(list);
        assertEquals(list.size(), 16);
    }

    @Test
    public void testGetById() {
        Account a = DAOFactory.getInstance().getAccountDAO().getById(3);
        assertNotNull(a);
        assertEquals(a.getBalance(), BigInteger.valueOf(-119273));

        a = DAOFactory.getInstance().getAccountDAO().getById(42);
        assertNull(a);

    }

    @Test
    public void testGetByType() {
        List<Account> list = DAOFactory.getInstance().getAccountDAO().getByType(1);
        assertNotNull(list);
        assertEquals(list.size(), 6);

        list = DAOFactory.getInstance().getAccountDAO().getByType(42);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testGetByBalanceRange() {
        BigInteger from = BigInteger.valueOf(0);
        BigInteger to = BigInteger.valueOf(15000);
        List<Account> list = DAOFactory.getInstance().getAccountDAO().getByBalanceRange(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 3);

        from = BigInteger.valueOf(30000);
        to = BigInteger.valueOf(15000);

        list = DAOFactory.getInstance().getAccountDAO().getByBalanceRange(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testGetByClient() {
        Client c = DAOFactory.getInstance().getClientDAO().getById(2);
        List<Account> list = DAOFactory.getInstance().getAccountDAO().getByClient(c);
        assertNotNull(list);
        assertEquals(list.size(), 1);

        c = DAOFactory.getInstance().getClientDAO().getById(1);
        list = DAOFactory.getInstance().getAccountDAO().getByClient(c);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testGetByBranch() {
        Branch b = DAOFactory.getInstance().getBranchDAO().getById(1);
        List<Account> list = DAOFactory.getInstance().getAccountDAO().getByBranch(b);
        assertNotNull(list);
        assertEquals(list.size(), 4);

        b = DAOFactory.getInstance().getBranchDAO().getById(4);
        list = DAOFactory.getInstance().getAccountDAO().getByBranch(b);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testGetByOperationsDate() {
        Date from = Date.valueOf("2015-03-31");
        Date to = Date.valueOf("2023-03-31");
        List<Account> list = DAOFactory.getInstance().getAccountDAO().getByOperationsDate(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 27);

        from = Date.valueOf("2023-03-31");
        to = Date.valueOf("2023-03-31");
        list = DAOFactory.getInstance().getAccountDAO().getByOperationsDate(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testGetByStringFilter() {
        String filter = "10";
        List<Account> list = DAOFactory.getInstance().getAccountDAO().getByStringFilter(filter);
        assertNotNull(list);
        assertEquals(list.size(), 1);

        filter = "12345";
        list = DAOFactory.getInstance().getAccountDAO().getByStringFilter(filter);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

}
