package com.exercise.product.category;

import com.exercise.product.category.query.GetAllCategoriesQuery;
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
public class GetAllCategoriesQueryHandlerTest {

    @InjectMocks
    private GetAllCategoriesQuery getAllCategoriesQuery;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    public void getAllProductsQuery_noArgs_returnsProductsArray() {
        // Given
        when(categoryRepository.findAll()).thenReturn(new ArrayList<Category>());
        when(categoryMapper.toDto(any(Category.class))).thenReturn(new CategoryDTO());

        List<CategoryDTO> categories = getAllCategoriesQuery.execute(null);
        assertEquals(new ArrayList<CategoryDTO>(), categories);
    }
}
