package com.exercise.product.unit.query;

import com.exercise.product.ProductRepository;
import com.exercise.product.Query;
import com.exercise.product.model.ProductDTO;
import com.exercise.product.model.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQuery implements Query<Void, List<ProductDTO>> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public GetAllProductsQuery(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> execute(Void unused) {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
