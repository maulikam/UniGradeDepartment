package com.example.demo.example1.mapper;

import com.example.demo.example1.dto.DriverDTO;
import com.example.demo.example1.model.Driver;

public class DriverMapper {

    public static DriverDTO toDTO(Driver driver) {
        if (driver == null) {
            return null;
        }

        return new DriverDTO(
                driver.getId(),
                driver.getName(),
                driver.getAge());
    }

    public static Driver toEntity(DriverDTO driverDTO) {
        if (driverDTO == null) {
            return null;
        }

        Driver driver = new Driver();
        driver.setId(driverDTO.getId());
        driver.setName(driverDTO.getName());
        driver.setAge(driverDTO.getAge());

        return driver;
    }
}

