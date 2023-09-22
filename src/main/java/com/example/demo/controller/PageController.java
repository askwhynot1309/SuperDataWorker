package com.example.demo.controller;

import com.example.demo.model.Apartment;
import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class PageController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ContactRepository contactRepository;

    private static final Logger logger = LogManager.getLogger(ListController.class);

    @GetMapping("/customerList")
    public String getCustomer(Model model){
        return "customerList";
    }

    @GetMapping("/contractList")
    public String getContact(Model model){
        return "contractList";
    }

    @GetMapping("/apartmentList")
    public String getApartment(Model model){
        return "apartmentList";
    }
}
