package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Operation;

import java.util.List;

public interface OperationDAO {
    void add(Operation operation);
    void update(Operation operation);
    void remove(Operation operation);

    List<Operation> getAll();
    Operation getById(Integer id);

    List<Operation> getByAccount(Account account);
}
