package com.exercise.product.command;

import com.exercise.product.ProductApplication;
import com.exercise.product.ProductRepository;
import com.exercise.product.model.Product;
import com.exercise.product.model.ProductCreationDTO;
import com.exercise.product.model.ProductMapper;
import com.exercise.product.category.Category;
import com.exercise.product.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductApplication.class)
public class CreateProductCommandHandlerTest {

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @Test
    public void createProductCommandHandler_emptyCategoryId_throwsCategoryNotFound() {
        //GIVEN
        ProductCreationDTO dto = new ProductCreationDTO();
        dto.setName("Name");
        dto.setDescription("Description");
        dto.setPrice(199.99);
        dto.setCategoryId(1);
        dto.setManufacturer("Manufacturer");

        Product expectedProduct = new Product();
        expectedProduct.setId(1);

        when(productMapper.toProduct(dto)).thenReturn(new Product());
        when(categoryRepository.findById(dto.getCategoryId())).thenReturn(Optional.empty());
        // WHEN/THEN
        Exception exception = assertThrows(RuntimeException.class, () -> createProductCommandHandler.execute(dto));

        // THEN
        assertEquals("no category found for id 1", exception.getMessage());
    }


    @Test
    public void createProductCommandHandler_validProduct_returnsSuccess() {
        //GIVEN
        ProductCreationDTO dto = new ProductCreationDTO();
        dto.setName("Name");
        dto.setDescription("Description");
        dto.setPrice(199.99);
        dto.setCategoryId(1);
        dto.setManufacturer("Manufacturer");

        Category category = new Category();
        category.setId(dto.getCategoryId());
        category.setName("Category");
        category.setDescription("Category Description");

        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(1);

        when(productMapper.toProduct(dto)).thenReturn(new Product());
        when(categoryRepository.findById(dto.getCategoryId())).thenReturn(Optional.of(category));
        when(productRepository.save(any())).thenReturn(expectedProduct);

        //WHEN
        Long id = createProductCommandHandler.execute(dto);

        // THEN
        assertEquals(1, id);
    }
}
