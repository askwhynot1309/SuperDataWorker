package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

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


    @Test
    void getCustomer() throws Exception{
        List<Customer> mockData = Arrays.asList(
                new Customer("C001", "Minh", "Dinh", "34 Ho Chi Minh", 20, "Active"),
                new Customer("C002", "Khoa", "Ly", "59 Ho Chi Minh", 20, "Active")
        );

        Customer customer = new Customer();
        customer.setID("C001");
        customer.setFirstName("Minh");
        customer.setLastName("Dinh");
        customer.setAddress("34 Ho Chi Minh");
        customer.setAge(20);
        customer.setStatus("Active");

        List<Customer> customers = List.of(customer);


        when(customerService.getAllCustomer()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/customerList"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void getApartment() {
    }

    @Test
    void getContract() {
    }
}