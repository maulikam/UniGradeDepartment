package com.example.demo.example1.service.impl;

import com.example.demo.example1.dto.DriverDTO;
import com.example.demo.example1.mapper.DriverMapper;
import com.example.demo.example1.model.Driver;
import com.example.demo.example1.repository.DriverRepository;
import com.example.demo.example1.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverDTO saveDriver(DriverDTO driverDto) {
        Driver driver = DriverMapper.toEntity(driverDto);
        driver = driverRepository.save(driver);
        return DriverMapper.toDTO(driver);
    }

    @Override
    public Optional<DriverDTO> getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(DriverMapper::toDTO);
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(DriverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDTO updateDriver(Long id, DriverDTO driverDetails) {
        return driverRepository.findById(id)
                .map(driver -> {
                    driver.setName(driverDetails.getName());
                    driver.setAge(driverDetails.getAge());
                    return DriverMapper.toDTO(driverRepository.save(driver));
                })
                .orElseThrow(() -> new RuntimeException("Driver not found with id " + id));
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
