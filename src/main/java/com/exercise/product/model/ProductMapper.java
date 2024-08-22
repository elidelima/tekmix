package com.exercise.product.model;

import com.exercise.product.unit.category.CategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setManufacturer(product.getManufacturer());
        dto.setCategory(categoryMapper.toDto(product.getCategory()));
        return dto;
    }

    public Product toProduct(ProductCreationDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setManufacturer(dto.getManufacturer());
        return product;
    }
}
