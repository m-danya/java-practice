package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.AccountType;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;


public interface AccountDAO {
    void add(Account account);

    void update(Account account);

    void remove(Account account);

    List<Account> getAll();

    Account getById(Integer id);

    List<Account> getByType(Integer type);

    List<Account> getByBalanceRange(BigInteger from, BigInteger to);

    List<Account> getByClient(Client client);

    List<Account> getByBranch(Branch branch);

    List<Account> getByOperationsDate(Date from, Date to);

    List<Account> getByStringFilter(String filter);

    AccountType getAccountType(Integer type);
}
