package com.exercise.product.model;

import com.exercise.product.category.Category;
import com.exercise.product.category.CategoryDTO;
import com.exercise.product.category.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductMapperTest {

    @InjectMocks
    private ProductMapper productMapper;

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    public void productMapper_validCreationDto_returnsValidProduct() {
        // Given
        ProductCreationDTO dto = new ProductCreationDTO();
        dto.setName("Name");
        dto.setDescription("Description");
        dto.setPrice(199.99);
        dto.setCategoryId(1);
        dto.setManufacturer("Manufacturer");

        Product expectedProduct = new Product();
        expectedProduct.setName("Name");
        expectedProduct.setDescription("Description");
        expectedProduct.setPrice(199.99);
        expectedProduct.setManufacturer("Manufacturer");

        // When
		Product product = productMapper.toProduct(dto);

        // Then
        assertEquals(expectedProduct, product);
    }

    @Test
    public void productMapper_validProduct_returnsValidDto() {
        // Given
        Category category = new Category();
        category.setId(1);
        category.setName("Category Name");
        category.setDescription("Category Description");

        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setDescription("Description");
        product.setPrice(199.99);
        product.setCategory(category);
        product.setManufacturer("Manufacturer");

        CategoryDTO expectedCategory = new CategoryDTO();
        expectedCategory.setId(1);
        expectedCategory.setName("Category Name");
        expectedCategory.setDescription("Category Description");

        ProductDTO expectedDto = new ProductDTO();
        expectedDto.setId(1);
        expectedDto.setName("Name");
        expectedDto.setDescription("Description");
        expectedDto.setPrice(199.99);
        expectedDto.setCategory(expectedCategory);
        expectedDto.setManufacturer("Manufacturer");

        when(categoryMapper.toDto(product.getCategory())).thenReturn(expectedCategory);

        // When
        ProductDTO dto = productMapper.toDto(product);

        // Then
        assertEquals(expectedDto, dto);
    }

}
