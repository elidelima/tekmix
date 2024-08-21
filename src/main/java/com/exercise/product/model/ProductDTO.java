package com.exercise.product.model;

import com.exercise.product.category.Category;
import com.exercise.product.category.CategoryDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private double price;
    private CategoryDTO category;
    private String manufacturer;
}
