package com.example.demo.service;

import com.example.demo.helper.CSVHelper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CSVHelper csvHelper;

    @Autowired
    CustomerRepository customerRepository;

    public void save(MultipartFile file) {
        try {
            List<Customer> customer = csvHelper.addCustomer(file.getInputStream());
            customerRepository.saveAll(customer);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomer() {
        List<Customer> listCustomers = new ArrayList<>();
        listCustomers = customerRepository.findAll();
        return listCustomers;
    }

    public Customer findById(String customerID) {
        return customerRepository.findById(customerID).orElse(null);
    }
}
