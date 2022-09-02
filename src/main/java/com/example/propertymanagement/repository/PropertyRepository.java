package com.example.propertymanagement.repository;

import com.example.propertymanagement.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
