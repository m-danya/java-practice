package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Operation;

public interface OperationDAO {
    void add(Operation operation);
    void update(Operation operation);
    void remove(Operation operation);
}
