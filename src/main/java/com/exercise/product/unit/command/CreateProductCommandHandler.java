package com.exercise.product.unit.command;

import com.exercise.product.Command;
import com.exercise.product.ProductRepository;
import com.exercise.product.unit.category.Category;
import com.exercise.product.unit.category.CategoryRepository;
import com.exercise.product.model.Product;
import com.exercise.product.model.ProductCreationDTO;
import com.exercise.product.model.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateProductCommandHandler implements Command<ProductCreationDTO, Long> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public CreateProductCommandHandler(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Long execute(ProductCreationDTO dto) {
        Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
        if (category.isEmpty()) {
            throw new Error("no category found for id " + dto.getCategoryId());
        }

        Product product = productMapper.toProduct(dto);
        product.setCategory(category.get());
        return (productRepository.save(product)).getId();
    }
}
