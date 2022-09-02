package com.example.propertymanagement.repository;

import com.example.propertymanagement.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<Lease, Integer> {
}
