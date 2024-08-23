package com.exercise.product.model;

import com.exercise.product.category.CategoryDTO;
import lombok.Data;

@Data
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private double price;
    private CategoryDTO category;
    private String manufacturer;
}
