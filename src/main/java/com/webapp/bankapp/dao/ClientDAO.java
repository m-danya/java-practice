package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

public interface ClientDAO {
    void add(Client client);
    void update(Client client);
    void remove(Client client);

    Client getById(Integer id);
    List<Client> getAll();

    List<Client> getByAccountType(Integer typeBeingSearchedFor);
    List<Client> getByOperationsDate(Date from, Date to);
    List<Client> getByTotalBalanceRange(BigInteger from, BigInteger to);
    List<Client> getByStringFilter(String filter);

    List<Client> getByBranch(Branch branch);
}
