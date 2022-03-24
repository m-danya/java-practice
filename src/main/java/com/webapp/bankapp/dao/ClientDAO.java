package com.webapp.bankapp.dao;

import com.webapp.bankapp.models.Client;

public interface ClientDAO {
    void add(Client client);
    void update(Client client);
    void remove(Client client);
}
