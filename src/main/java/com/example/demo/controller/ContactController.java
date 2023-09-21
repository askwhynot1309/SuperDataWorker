package com.example.demo.controller;

import com.example.demo.helper.CSVHelper;
import com.example.demo.message.ResponseMessage;
import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactService contactService;

    @Autowired
    CustomerRepository cus;

    @GetMapping("/{id}")
    public Customer getContactById(@PathVariable("id") String id) {
        Optional<Customer> result = cus.findById(id);

        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file) {
        String message = "";
        int code;


        if (CSVHelper.hasCSVFormat(file)) {
            try {
                contactService.save(file);
                code = 200;
                List<Contact> data = contactService.getAllContact();
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,data,code));
            } catch (Exception e) {
                code = 409;
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, null, code));
            }
        }
        code = 204;
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, null, code));
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Contact>> getAllContact() {
        try {
            List<Contact> tutorials = contactService.getAllContact();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
