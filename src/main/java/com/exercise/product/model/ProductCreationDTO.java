package com.exercise.product.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductCreationDTO {
    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "categoryId cannot be null")
    private Integer categoryId;

    @Min(message = "Price cannot be negative", value = 0)
    private double price;

    private Region region;

    private String manufacturer;
}
