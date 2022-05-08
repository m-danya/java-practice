package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;

import java.util.List;

public interface BranchDAO {
    void add(Branch branch);

    void update(Branch branch);

    void remove(Branch branch);

    List<Branch> getAll();

    Branch getById(Integer id);

    List<Branch> getByStringFilter(String filter);
}
