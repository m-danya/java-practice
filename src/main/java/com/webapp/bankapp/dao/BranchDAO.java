package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Branch;

public interface BranchDAO {
    void add(Branch branch);
    void update(Branch branch);
    void remove(Branch branch);
}
