package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.AccountType;

import java.math.BigInteger;
import java.util.List;

public interface AccountDAO {
    void add(Account account);
    void update(Account account);
    void remove(Account account);

    List<Account> find_by_type(AccountType type);
    List<Account> find_by_balance_range(BigInteger from, BigInteger to);
}
