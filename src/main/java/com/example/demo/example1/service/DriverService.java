package com.example.demo.example1.service;

import com.example.demo.example1.dto.DriverDTO;
import java.util.List;
import java.util.Optional;

public interface DriverService {

    // Create or Update a driver
    DriverDTO saveDriver(DriverDTO driver);

    // Retrieve a driver by ID
    Optional<DriverDTO> getDriverById(Long id);

    // Retrieve all drivers
    List<DriverDTO> getAllDrivers();

    // Update a driver
    DriverDTO updateDriver(Long id, DriverDTO driverDetails);

    // Delete a driver by ID
    void deleteDriver(Long id);
}

