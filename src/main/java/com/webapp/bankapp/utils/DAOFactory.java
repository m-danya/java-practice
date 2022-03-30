package com.webapp.bankapp.utils;


import com.webapp.bankapp.dao.AccountDAO;
import com.webapp.bankapp.dao.BranchDAO;
import com.webapp.bankapp.dao.ClientDAO;
import com.webapp.bankapp.dao.OperationDAO;
import com.webapp.bankapp.dao.implementations.AccountDAOImplementation;
import com.webapp.bankapp.dao.implementations.BranchDAOImplementation;
import com.webapp.bankapp.dao.implementations.ClientDAOImplementation;
import com.webapp.bankapp.dao.implementations.OperationDAOImplementation;
import org.springframework.stereotype.Component;

@Component("DAOFactory")
public class DAOFactory {
    private static ClientDAO clientDAO = null;
    private static BranchDAO branchDAO = null;
    private static AccountDAO accountDAO = null;
    private static OperationDAO operationDAO = null;

    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance(){
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public ClientDAO getClientDAO(){
        if (clientDAO == null) {
            clientDAO = new ClientDAOImplementation();
        }
        return clientDAO;
    }
    public BranchDAO getBranchDAO(){
        if (branchDAO == null) {
            branchDAO = new BranchDAOImplementation();
        }
        return branchDAO;
    }

    public OperationDAO getOperationDAO(){
        if (operationDAO == null) {
            operationDAO = new OperationDAOImplementation();
        }
        return operationDAO;
    }
    public AccountDAO getAccountDAO(){
        if (accountDAO == null) {
            accountDAO = new AccountDAOImplementation();
        }
        return accountDAO;
    }

}