package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Account;

public interface AccountDAO {
    void add(Account account);
    void update(Account account);
    void remove(Account account);
}
