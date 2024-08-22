package com.exercise.product.unit.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreationDTO {
    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "description cannot be null")
    private String description;
}
