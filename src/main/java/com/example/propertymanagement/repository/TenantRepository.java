package com.example.propertymanagement.repository;

import com.example.propertymanagement.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant,Integer> {
}
