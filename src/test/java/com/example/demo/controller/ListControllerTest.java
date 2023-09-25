package com.example.demo.controller;

import com.example.demo.model.Apartment;
import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.ApartmentService;
import com.example.demo.service.ContactService;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ExtendWith(SpringExtension.class)
class ListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    ApartmentService apartmentService;
    @MockBean
    ApartmentRepository apartmentRepository;

    @MockBean
    ContactService contactService;
    @MockBean
    ContactRepository contactRepository;

    @Test
    void getCustomer() throws Exception{
        Customer customer = new Customer();
        customer.setID("C001");
        customer.setFirstName("Minh");
        customer.setLastName("Dinh");
        customer.setAddress("34 Ho Chi Minh");
        customer.setAge(20);
        customer.setStatus("Active");

        List<Customer> customers = List.of(customer);


        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customerList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Successfully"))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andReturn();
    }

    @Test
    void getApartment() throws Exception {
        Apartment apartment = new Apartment();
        apartment.setId("A001");
        apartment.setAddress("98835 La Follette Pass");
        apartment.setRentalPrice(2000);
        apartment.setNumberOfRoom(3);


        List<Apartment> apartments = List.of(apartment);


        when(apartmentRepository.findAll()).thenReturn(apartments);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/apartmentList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Successfully"))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andReturn();
    }

    @Test
    void getContract() throws Exception {
        Contact contact = new Contact();

        List<Contact> contacts = List.of(contact);

        when(contactService.getAllContact()).thenReturn(contacts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contactList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Successfully"))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andReturn();
    }

    @Test
    public void testGetCustomerError() throws Exception {
        // Mocking the customerRepository to throw an exception
        when(customerRepository.findAll()).thenThrow(new RuntimeException("Internal server error"));

        // Perform the GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customerList")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("Internal server error"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    public void testGetContactError() throws Exception {
        when(contactService.getAllContact()).thenThrow(new RuntimeException("Internal server error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contactList")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("Internal server error"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    public void testGetApartmentError() throws Exception {
        // Mocking the customerRepository to throw an exception
        when(apartmentRepository.findAll()).thenThrow(new RuntimeException("Internal server error"));

        // Perform the GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/apartmentList")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("Internal server error"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

}