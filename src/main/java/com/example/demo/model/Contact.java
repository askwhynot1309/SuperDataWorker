package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Table(name = "contact")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = { "customer", "apartment" }, allowSetters = true)
public class Contact {
    @Id
    @Column(name = "id", nullable = false, length = 45)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
//    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
//    @JsonIgnore
    private Apartment apartment;

    private LocalDate StartDate;
    private LocalDate EndDate;

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", StartDate=" + StartDate +
                ", EndDate=" + EndDate +
                '}';
    }

}
