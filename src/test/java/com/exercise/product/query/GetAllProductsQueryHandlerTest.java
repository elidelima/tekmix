package com.exercise.product.query;

import com.exercise.product.ProductRepository;
import com.exercise.product.model.Product;
import com.exercise.product.model.ProductDTO;
import com.exercise.product.model.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetAllProductsQueryHandlerTest {

    @InjectMocks
    private GetAllProductsQuery getAllProductsQuery;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Test
    public void getAllProductsQuery_noArgs_returnsProductsArray() {
        // Given
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>());
        when(productMapper.toDto(any(Product.class))).thenReturn(new ProductDTO());

        List<ProductDTO> products = getAllProductsQuery.execute(null);
        assertEquals(new ArrayList<ProductDTO>(), products);
    }
}
