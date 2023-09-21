package com.example.demo.controller;

import com.example.demo.model.Apartment;
import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class CustomerListController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/customerList")
    public String getCustomer(Model model){
        List<Customer> data = customerRepository.findAll();
        System.out.println(data);
        model.addAttribute("data", data);
        return "customerList";
    }

    @GetMapping("/apartmentList")
    public String getApartment(Model model){
        List<Apartment> data = apartmentRepository.findAll();
        System.out.println(data);
        model.addAttribute("data", data);
        return "apartmentList";
    }

    @GetMapping("/contractList")
    public String getContract(Model model){
        List<Contact> data = contactRepository.findAll();
        System.out.println(data);
        model.addAttribute("data", data);
        return "contractList";
    }
}
