package com.webapp.bankapp.controllers;

import com.webapp.bankapp.dao.AccountDAO;
import com.webapp.bankapp.dao.BranchDAO;
import com.webapp.bankapp.dao.ClientDAO;
import com.webapp.bankapp.dao.OperationDAO;
import com.webapp.bankapp.dao.implementations.AccountDAOImplementation;
import com.webapp.bankapp.dao.implementations.BranchDAOImplementation;
import com.webapp.bankapp.dao.implementations.ClientDAOImplementation;
import com.webapp.bankapp.dao.implementations.OperationDAOImplementation;
import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class AccountController {

    @Autowired
    private final AccountDAO accountDao = new AccountDAOImplementation();
    private final BranchDAO branchDAO = new BranchDAOImplementation();
    private final ClientDAO clientDAO = new ClientDAOImplementation();
    private final OperationDAO operationDao = new OperationDAOImplementation();

    @GetMapping("/accounts")
    public String accounts(
            @RequestParam(name = "filter_by", required = false) String filter_by,
            @RequestParam(name = "type1", required = false) Boolean type_1_is_allowed,
            @RequestParam(name = "type2", required = false) Boolean type_2_is_allowed,
            @RequestParam(name = "type3", required = false) Boolean type_3_is_allowed,
            @RequestParam(name = "type4", required = false) Boolean type_4_is_allowed,
            @RequestParam(name = "type5", required = false) Boolean type_5_is_allowed,
            @RequestParam(name = "date_from", required = false) String date_from,
            @RequestParam(name = "date_to", required = false) String date_to,
            @RequestParam(name = "sum_from", required = false) BigInteger sum_from,
            @RequestParam(name = "sum_to", required = false) BigInteger sum_to,
            @RequestParam(name = "str", required = false) String str,
            @RequestParam(name = "client_id", required = false) Integer client_id,
            @RequestParam(name = "branch_id", required = false) Integer branch_id,
            Model model) {
        List<Account> accounts = new ArrayList<Account>();
        if (Objects.equals(filter_by, "types")) {
            Map<Integer, Boolean> types = new HashMap<Integer, Boolean>();
            types.put(1, Objects.nonNull(type_1_is_allowed));
            types.put(2, Objects.nonNull(type_2_is_allowed));
            types.put(3, Objects.nonNull(type_3_is_allowed));
            types.put(4, Objects.nonNull(type_4_is_allowed));
            types.put(5, Objects.nonNull(type_5_is_allowed));
            for (Map.Entry<Integer, Boolean> item : types.entrySet()) {
                if (item.getValue()) {
                    List<Account> relevant_account = accountDao.getByType(item.getKey());
                    for (Account c : relevant_account) {
                        if (!accounts.contains(c)) {
                            accounts.add(c);
                        }
                    }
                }
            }
        } else if (Objects.equals(filter_by, "operations_range")) {
            Date d1 = Date.valueOf(date_from);
            Date d2 = Date.valueOf(date_to);
            accounts.addAll(accountDao.getByOperationsDate(d1, d2));
        } else if (Objects.equals(filter_by, "sum_range")) {
            accounts.addAll(accountDao.getByBalanceRange(sum_from, sum_to));
        } else if (Objects.equals(filter_by, "str")) {
            accounts.addAll(accountDao.getByStringFilter(str));
        } else if (Objects.equals(filter_by, "client")) {
            accounts.addAll(accountDao.getByClient(clientDAO.getById(client_id)));
        } else if (Objects.equals(filter_by, "branch_id")) {
            accounts.addAll(accountDao.getByBranch(branchDAO.getById(branch_id)));
        } else {
            // no filter params
            accounts.addAll(accountDao.getAll());
        }

        model.addAttribute("accounts", accounts);
        model.addAttribute("accountDAO", accountDao);
        return "accounts";
    }

    @GetMapping("/viewAccount")
    public String viewAccount(@RequestParam(name = "id") Integer id, Model model) {
        Account account = accountDao.getById(id);
        model.addAttribute("account", account);
        List<Operation> operations = operationDao.getByAccount(account);
        model.addAttribute("operations", operations);
        model.addAttribute("accountDAO", accountDao);
        model.addAttribute("operationDAO", operationDao);
        return "viewAccount";
    }

    @GetMapping("/addAccount")
    public String addAccount(Model model) {
        return "addAccount";
    }

    @PostMapping("/updateAccount")
    public String updateClient(@RequestParam(name = "id") Integer id,
                               @RequestParam(name = "balance") BigInteger balance,
                               @RequestParam(name = "type") Integer type,
                               @RequestParam(name = "branch") Integer branch,
                               @RequestParam(name = "client") Integer client,
                               @RequestParam(name = "interest_yield_receiver", required = false) Integer interest_yield_receiver,
                               @RequestParam(name = "is_active") Boolean is_active,
                               Model model
    ) {
        Account a = accountDao.getById(id);
        a.setBalance(balance);
        a.setType(accountDao.getAccountType(type));
        a.setBranch(branchDAO.getById(branch));
        a.setClient(clientDAO.getById(client));
        if (interest_yield_receiver != null) {
            a.setInterest_yield_receiver(accountDao.getById(interest_yield_receiver));
        }
        a.setIs_active(is_active);
        accountDao.update(a);
        return viewAccount(id, model);
    }

    @PostMapping("/saveAccount")
    public String saveAccount(
            @RequestParam(name = "balance") BigInteger balance,
            @RequestParam(name = "type") Integer type,
            @RequestParam(name = "branch") Integer branch,
            @RequestParam(name = "client") Integer client,
            @RequestParam(name = "interest_yield_receiver") Integer interest_yield_receiver,
            @RequestParam(name = "is_active") Boolean is_active,
            Model model
    ) {
        Account a = new Account(balance,
                accountDao.getAccountType(type),
                branchDAO.getById(branch),
                clientDAO.getById(client),
                accountDao.getById(interest_yield_receiver),
                is_active
        );
        accountDao.add(a);
        return viewAccount(a.getId(), model);
    }

    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestParam(name = "id") Integer id, Model model) {
        Account a = accountDao.getById(id);
        accountDao.remove(a);
        return "redirect:/accounts";
    }


    @GetMapping("/viewOperation")
    public String viewOperation(@RequestParam(name = "id") Integer id, Model model) {
        Operation operation = operationDao.getById(id);
        model.addAttribute("operation", operation);
        model.addAttribute("operationDAO", operationDao);
        return "viewOperation";
    }

    @GetMapping("/addOperation")
    public String addOperation(@RequestParam(name = "account_id") Integer account_id, Model model) {
        model.addAttribute("account_id", account_id);
        return "addOperation";
    }

    @PostMapping("/updateOperation")
    public String updateClient(@RequestParam(name = "id") Integer id,
                               @RequestParam(name = "amount") BigInteger amount,
                               @RequestParam(name = "account") Integer account_id,
                               @RequestParam(name = "timestamp") String timestamp,
                               Model model
    ) {
        Operation a = operationDao.getById(id);
        a.setAmount(amount);
        a.setAccount(accountDao.getById(account_id));
        a.setTimestamp(Timestamp.valueOf(timestamp));
        operationDao.update(a);
        return viewOperation(id, model);
    }

    @PostMapping("/saveOperation")
    public String saveOperation(
            @RequestParam(name = "amount") BigInteger amount,
            @RequestParam(name = "account") Integer account_id,
            @RequestParam(name = "timestamp") String timestamp,
            Model model
    ) {
        Operation a = new Operation(amount, accountDao.getById(account_id), Timestamp.valueOf(timestamp));
        operationDao.add(a);
        return viewOperation(a.getId(), model);
    }

    @PostMapping("/deleteOperation")
    public String deleteOperation(@RequestParam(name = "id") Integer id, Model model) {
        Operation a = operationDao.getById(id);
        Integer account_id = a.getAccount().getId();
        operationDao.remove(a);
        return "redirect:/viewAccount?id=" + account_id;
    }


}
