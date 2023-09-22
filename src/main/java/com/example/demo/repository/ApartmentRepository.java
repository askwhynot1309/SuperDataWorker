package com.example.demo.repository;

import com.example.demo.model.Apartment;
import com.example.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, String> {
    boolean existsById(String id);
    Optional<Apartment> findById(String id);

}
