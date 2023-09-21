package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String ID;
    private String FirstName;
    private String LastName;
    private String Address;
    private int Age;
    private String status;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Contact> contact;


    public Customer(String ID, String firstName, String lastName, String address, int age, String status) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Age = age;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + ID + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", Age=" + Age +
                ", status='" + status + '\'' +
                '}';
    }
}
