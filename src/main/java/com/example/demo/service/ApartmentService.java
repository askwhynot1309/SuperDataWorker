package com.example.demo.service;

import com.example.demo.helper.CSVHelper;
import com.example.demo.model.Apartment;
import com.example.demo.model.Customer;
import com.example.demo.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    CSVHelper csvHelper;

    public void save(MultipartFile file) {
        try {
            List<Apartment> apartment= csvHelper.addApartment(file.getInputStream());
            apartmentRepository.saveAll(apartment);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Apartment> getAllApartment() {
        return apartmentRepository.findAll();
    }

    public Apartment findById(String customerID) {
        return apartmentRepository.findById(customerID).orElse(null);
    }
}
