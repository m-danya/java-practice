package com.webapp.bankapp.controllers;

import com.webapp.bankapp.dao.BranchDAO;
import com.webapp.bankapp.dao.ClientDAO;
import com.webapp.bankapp.dao.implementations.BranchDAOImplementation;
import com.webapp.bankapp.dao.implementations.ClientDAOImplementation;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
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
public class ClientController {

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImplementation();
    private final BranchDAO branchDAO = new BranchDAOImplementation();


    @GetMapping("/clients")
    public String clients(
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
            @RequestParam(name = "branch_id", required = false) Integer branch_id,
            Model model) {
        List<Client> clients = new ArrayList<Client>();

        if (Objects.equals(filter_by, "types")) {
            Map<Integer, Boolean> types = new HashMap<Integer, Boolean>();
            types.put(1, Objects.nonNull(type_1_is_allowed));
            types.put(2, Objects.nonNull(type_2_is_allowed));
            types.put(3, Objects.nonNull(type_3_is_allowed));
            types.put(4, Objects.nonNull(type_4_is_allowed));
            types.put(5, Objects.nonNull(type_5_is_allowed));
            for (Map.Entry<Integer, Boolean> item : types.entrySet()) {
                if (item.getValue()) {
                    List<Client> relevant_clients = clientDAO.getByAccountType(item.getKey());
                    for (Client c : relevant_clients) {
                        if (!clients.contains(c)) {
                            clients.add(c);
                        }
                    }
                }
            }
        } else if (Objects.equals(filter_by, "operations_range")) {
            Date d1 = Date.valueOf(date_from);
            Date d2 = Date.valueOf(date_to);
            clients.addAll(clientDAO.getByOperationsDate(d1, d2));
        } else if (Objects.equals(filter_by, "sum_range")) {
            clients.addAll(clientDAO.getByTotalBalanceRange(sum_from, sum_to));
        } else if (Objects.equals(filter_by, "str")) {
            clients.addAll(clientDAO.getByStringFilter(str));
        } else if (Objects.equals(filter_by, "branch_id")) {
            clients.addAll(clientDAO.getByBranch(branchDAO.getById(branch_id)));
        } else {
            // no filter params
            clients.addAll(clientDAO.getAll());
        }
        model.addAttribute("clients", clients);
        model.addAttribute("clientDAO", clientDAO);
        return "clients";
    }

    @GetMapping("/viewClient")
    public String viewClient(@RequestParam(name = "id") Integer id, Model model) {
        Client client = clientDAO.getById(id);
        model.addAttribute("client", client);
        model.addAttribute("clientDAO", clientDAO);
        return "viewClient";
    }

    @GetMapping("/addClient")
    public String addClient(Model model) {
        return "addClient";
    }

    @PostMapping("/updateClient")
    public String updateClient(@RequestParam(name = "id") Integer id,
                               @RequestParam(name = "type") String type,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "middle_name") String middle_name,
                               @RequestParam(name = "surname") String surname,
                               @RequestParam(name = "address") String address,
                               @RequestParam(name = "phone_number") String phone_number,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "date_of_birth") String date_of_birth,
                               Model model
    ) {
        Client c = clientDAO.getById(id);
        c.setType(type);
        c.setName(name);
        c.setMiddle_name(middle_name);
        c.setSurname(surname);
        c.setAddress(address);
        c.setPhone_number(phone_number);
        c.setEmail(email);
        c.setDate_of_birth(Date.valueOf(date_of_birth));
        clientDAO.update(c);
        return viewClient(id, model);
    }

    @PostMapping("/saveClient")
    public String saveClient(
            @RequestParam(name = "type") String type,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "middle_name") String middle_name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "phone_number") String phone_number,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "date_of_birth") String date_of_birth,
            Model model
    ) {
        Client c = new Client(type, name, middle_name, surname, address, phone_number, email, Date.valueOf(date_of_birth));
        clientDAO.add(c);
        return viewClient(c.getId(), model);
    }

    @PostMapping("/deleteClient")
    public String deleteClient(@RequestParam(name = "id") Integer id, Model model) {
        Client c = clientDAO.getById(id);
        clientDAO.remove(c);
        return "redirect:/clients";
    }

}
