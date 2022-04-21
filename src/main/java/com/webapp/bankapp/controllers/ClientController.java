package com.webapp.bankapp.controllers;

import com.webapp.bankapp.dao.ClientDAO;
import com.webapp.bankapp.dao.implementations.ClientDAOImplementation;
import com.webapp.bankapp.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImplementation();


    @GetMapping("/clients")
    public String clients(Model model) {
        List<Client> clients = clientDAO.getAll();

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

    @PostMapping("/deleteClient")
    public String deleteClient(@RequestParam(name = "id") Integer id, Model model) {
        Client c = clientDAO.getById(id);
        clientDAO.remove(c);
        return "redirect:/clients";
    }

}
