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
public class ClientDAOTest {
    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private DAOFactory daoFactory;

    @Test
    public void testGetAll() {
        List<Client> list;
        list =  DAOFactory.getInstance().getClientDAO().getAll();
        assertNotNull(list);
        assertEquals(list.size(), 12);
    }

    @Test
    public void testGetById() {
        Client c = DAOFactory.getInstance().getClientDAO().getById(3);
        assertNotNull(c);
        assertEquals(c.getAddress(), "Россия, г. Магнитогорск, Восточная ул., д. 8 кв.144");

        c = DAOFactory.getInstance().getClientDAO().getById(42);
        assertNull(c);
    }

    @Test
    public void testGetByAccountType() {
        List<Client> list = DAOFactory.getInstance().getClientDAO().getByAccountType(1);
        assertNotNull(list);
        assertEquals(list.size(), 6);

        list = DAOFactory.getInstance().getClientDAO().getByAccountType(123);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testGetByOperationsDate() {
        Date from = Date.valueOf("2015-03-31");
        Date to = Date.valueOf("2023-03-31");
        List<Client> list = DAOFactory.getInstance().getClientDAO().getByOperationsDate(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 27);

        from = Date.valueOf("2023-03-31");
        to = Date.valueOf("2023-03-31");
        list = DAOFactory.getInstance().getClientDAO().getByOperationsDate(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }


    @Test
    public void testGetByBalanceRange() {
        BigInteger from = BigInteger.valueOf(0);
        BigInteger to = BigInteger.valueOf(15000);
        List<Client> list = DAOFactory.getInstance().getClientDAO().getByTotalBalanceRange(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 2);

        from = BigInteger.valueOf(30000);
        to = BigInteger.valueOf(15000);

        list = DAOFactory.getInstance().getClientDAO().getByTotalBalanceRange(from, to);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }


    @Test
    public void testGetByStringFilter() {
        String filter = "10";
        List<Client> list = DAOFactory.getInstance().getClientDAO().getByStringFilter(filter);
        assertNotNull(list);
        assertEquals(list.size(), 2);

        filter = "12345";
        list = DAOFactory.getInstance().getClientDAO().getByStringFilter(filter);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }


    @Test
    public void testGetByBranch() {
        Branch b = DAOFactory.getInstance().getBranchDAO().getById(1);
        List<Client> list = DAOFactory.getInstance().getClientDAO().getByBranch(b);
        assertNotNull(list);
        assertEquals(list.size(), 4);

        b = DAOFactory.getInstance().getBranchDAO().getById(4);
        list = DAOFactory.getInstance().getClientDAO().getByBranch(b);
        assertNotNull(list);
        assertEquals(list.size(), 0);
    }

}
