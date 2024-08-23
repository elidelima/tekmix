package com.exercise.product.category;

import com.exercise.product.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CategoryMapperTest {

    @InjectMocks
    private CategoryMapper categoryMapper;

    @Test
    public void categoryMapper_validCreationDto_returnsValidCategory() {
        // Given
        CategoryCreationDTO dto = new CategoryCreationDTO();
        dto.setName("Category Name");
        dto.setDescription("Category Description");

        Category expectedCategory = new Category();
        expectedCategory.setName("Category Name");
        expectedCategory.setDescription("Category Description");

        // When
        Category category = categoryMapper.toCategory(dto);

        // Then
        assertEquals(expectedCategory, category);
    }

    @Test
    public void categoryMapper_validCategory_returnsValidDto() {
        // Given
        Category category = new Category();
        category.setId(1);
        category.setName("Category Name");
        category.setDescription("Category Description");

        CategoryDTO expectedDto = new CategoryDTO();
        expectedDto.setId(1);
        expectedDto.setName("Category Name");
        expectedDto.setDescription("Category Description");

        // When
        CategoryDTO dto = categoryMapper.toDto(category);

        // Then
        assertEquals(expectedDto, dto);
    }
}
