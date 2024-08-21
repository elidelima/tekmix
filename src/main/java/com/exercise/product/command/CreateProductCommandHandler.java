package com.exercise.product.command;

import com.exercise.product.Command;
import com.exercise.product.ProductRepository;
import com.exercise.product.category.Category;
import com.exercise.product.category.CategoryRepository;
import com.exercise.product.model.Product;
import com.exercise.product.model.ProductCreationDTO;
import com.exercise.product.model.ProductDTO;
import com.exercise.product.model.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateProductCommandHandler implements Command<ProductCreationDTO, ProductDTO> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public CreateProductCommandHandler(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO execute(ProductCreationDTO dto) {
        Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
        if (category.isEmpty()) {
            throw new Error("no category found for id " + dto.getCategoryId());
        }

        Product product = productMapper.toProduct(dto);
        product.setCategory(category.get());
        return productMapper.toDto(productRepository.save(product));
    }
}
