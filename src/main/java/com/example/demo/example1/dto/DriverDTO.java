package com.example.demo.example1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object for driver information.")
public class DriverDTO implements Serializable {

    @Schema(description = "Unique identifier for the driver.", example = "1", required = true)
    private Long id;

    @Schema(description = "Name of the driver.", example = "John Doe", required = true)
    private String name;

    @Schema(description = "Age of the driver.", example = "35", required = false)
    private Integer age;

    public static class Fields {

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";

        private Fields() {
        }
    }
}
