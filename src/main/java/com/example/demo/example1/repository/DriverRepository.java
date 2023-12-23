package com.example.demo.example1.repository;

import com.example.demo.example1.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}

