package com.exercise.product.category;

import com.exercise.product.ProductApplication;
import com.exercise.product.category.command.CreateCategoryCommandHandler;
import com.exercise.product.command.CreateProductCommandHandler;
import com.exercise.product.model.Product;
import com.exercise.product.model.ProductCreationDTO;
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
public class CreateCategoryCommandHandlerTest {

    @InjectMocks
    private CreateCategoryCommandHandler createCategoryCommandHandler;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    public void createCategoryCommandHandler_validCategory_returnsNewCategoryId() {
        //GIVEN
        CategoryCreationDTO dto = new CategoryCreationDTO();
        dto.setName("Category");
        dto.setDescription("Category Description");

        Category expectedCategory = new Category();
        expectedCategory.setId(2);

        when(categoryMapper.toCategory(dto)).thenReturn(new Category());
        when(categoryRepository.save(any())).thenReturn(expectedCategory);

        //WHEN
        Integer id = createCategoryCommandHandler.execute(dto);

        // THEN
        assertEquals(2, id);
    }
}
