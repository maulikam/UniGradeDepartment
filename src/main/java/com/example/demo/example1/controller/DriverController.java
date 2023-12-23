package com.example.demo.example1.controller;

import com.example.demo.example1.dto.DriverDTO;
import com.example.demo.example1.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@Tag(name = "Driver API", description = "API endpoints for managing drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @Operation(summary = "Create a new driver")
    @ApiResponse(responseCode = "201", description = "Driver created",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = DriverDTO.class))})
    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(
            @Parameter(description = "Driver details for the new driver to be created.", required = true)
            @RequestBody DriverDTO driverDTO) {
        DriverDTO savedDriverDTO = driverService.saveDriver(driverDTO);
        return new ResponseEntity<>(savedDriverDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a driver by ID")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = DriverDTO.class))})
    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getDriverById(
            @Parameter(description = "ID of the driver to be retrieved.", required = true)
            @PathVariable Long id) {
        return driverService.getDriverById(id)
                .map(driverDTO -> new ResponseEntity<>(driverDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all drivers")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))})
    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<DriverDTO> driverDTOs = driverService.getAllDrivers();
        return new ResponseEntity<>(driverDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing driver")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = DriverDTO.class))})
    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> updateDriver(
            @Parameter(description = "ID of the driver to be updated.", required = true)
            @PathVariable Long id,
            @Parameter(description = "Details of the driver to be updated.", required = true)
            @RequestBody DriverDTO driverDTO) {
        try {
            DriverDTO updatedDriverDTO = driverService.updateDriver(id, driverDTO);
            return new ResponseEntity<>(updatedDriverDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deletes a driver")
    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(
            @Parameter(description = "ID of the driver to be deleted.", required = true)
            @PathVariable Long id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
