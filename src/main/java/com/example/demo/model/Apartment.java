package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "apartment")
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {
    @Id
    @Column(name = "apartment_id")
    private String id;
    private String address;
    private int RentalPrice;
    private int numberOfRoom;

    @OneToMany(mappedBy = "apartment")
    @JsonIgnore
    private List<Contact> contact;

    public Apartment(String id, String address, int rentalPrice, int numberOfRoom) {
        this.id = id;
        this.address = address;
        RentalPrice = rentalPrice;
        this.numberOfRoom = numberOfRoom;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", RentalPrice=" + RentalPrice +
                ", numberOfRoom=" + numberOfRoom +
                '}';
    }
}
