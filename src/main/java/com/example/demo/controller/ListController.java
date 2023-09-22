package com.example.demo.controller;

import com.example.demo.message.ResponseMessage;
import com.example.demo.model.Apartment;
import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.ContactService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ListController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ContactService contactService;

    private static final Logger logger = LogManager.getLogger(ListController.class);

    @GetMapping("api/customerList")
    public ResponseEntity<ResponseMessage> getCustomer(){
        try {
            List<Customer> data = customerRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage("Successfully", data, 200)
            );
        } catch (Exception e) {
            logger.error("Failed to show customer: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMessage("Internal server error", null, 500)
            );
        }
    }

    @GetMapping("api/apartmentList")
    public ResponseEntity<ResponseMessage> getApartment(){
        try {
            List<Apartment> data = apartmentRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage("Successfully", data, 200)
            );
        } catch (Exception e) {
            logger.error("Failed to show customer: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMessage("Internal server error", null, 500)
            );
        }
    }

    @GetMapping("api/contactList")
    public ResponseEntity<ResponseMessage> getContact(){
        try {
            List<Contact> data = contactService.getAllContact();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage("Successfully", data, 200)
            );
        } catch (Exception e) {
            logger.error("Failed to show customer: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMessage("Internal server error", null, 500)
            );
        }
    }
}
