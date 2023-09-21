package com.example.demo.service;

import com.example.demo.helper.CSVHelper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CSVRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CSVRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    public void save(MultipartFile file) {
        try {
            List<Customer> customer = CSVHelper.addCustomer(file.getInputStream());
            repository.saveAll(customer);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomer() {
        return repository.findAll();
    }

    public Customer findById(String customerID) {
        return customerRepository.findById(customerID).orElse(null);
    }
}
