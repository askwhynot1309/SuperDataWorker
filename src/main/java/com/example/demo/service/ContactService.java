package com.example.demo.service;

import com.example.demo.helper.CSVHelper;
import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CSVHelper csvHelper;

    public void save(MultipartFile file) {
        try {
            List<Contact> contacts = csvHelper.addContact(file.getInputStream());
            repository.saveAll(contacts);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Contact> getAllContact() {
        List<Contact> listContract = new ArrayList<>();
        listContract = contactRepository.findAll();
        return listContract;
    }
}
