package com.webapp.bankapp.controllers;

import com.webapp.bankapp.dao.AccountDAO;
import com.webapp.bankapp.dao.BranchDAO;
import com.webapp.bankapp.dao.ClientDAO;
import com.webapp.bankapp.dao.implementations.AccountDAOImplementation;
import com.webapp.bankapp.dao.implementations.BranchDAOImplementation;
import com.webapp.bankapp.dao.implementations.ClientDAOImplementation;
import com.webapp.bankapp.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.sql.Date;
import java.util.*;

@Controller
public class BranchController {

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImplementation();

    @Autowired
    private final AccountDAO accountDao = new AccountDAOImplementation();

    @Autowired
    private final BranchDAO branchDao = new BranchDAOImplementation();

    @GetMapping("/branches")
    public String branches(
            @RequestParam(name = "filter_by", required = false) String filter_by,
            @RequestParam(name = "str", required = false) String str,
            Model model) {
        List<Branch> branches = new ArrayList<Branch>();
        if (Objects.equals(filter_by, "str")) {
            branches.addAll(branchDao.getByStringFilter(str));
        } else {
            // no filter params
            branches.addAll(branchDao.getAll());
        }

        model.addAttribute("branches", branches);
        model.addAttribute("branchDAO", branchDao);
        return "branches";
    }

    @GetMapping("/viewBranch")
    public String viewBranch(@RequestParam(name = "id") Integer id, Model model) {
        Branch branch = branchDao.getById(id);
        model.addAttribute("branch", branch);
        model.addAttribute("branchDAO", branchDao);
        return "viewBranch";
    }

    @GetMapping("/addBranch")
    public String addBranch(Model model) {
        return "addBranch";
    }

    @PostMapping("/updateBranch")
    public String updateClient(@RequestParam(name = "id") Integer id,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "address") String address,
                               Model model
    ) {
        Branch b = branchDao.getById(id);
        b.setName(name);
        b.setAddress(address);
        branchDao.update(b);
        return viewBranch(id, model);
    }

    @PostMapping("/saveBranch")
    public String saveBranch(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "address") String address,
            Model model
    ) {
        Branch b = new Branch(name, address);
        branchDao.add(b);
        return viewBranch(b.getId(), model);
    }

    @PostMapping("/deleteBranch")
    public String deleteBranch(@RequestParam(name = "id") Integer id, Model model) {
        Branch a = branchDao.getById(id);
        branchDao.remove(a);
        return "redirect:/branches";
    }


}
