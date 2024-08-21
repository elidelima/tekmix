package com.exercise.product.category.command;

import com.exercise.product.Command;
import com.exercise.product.category.*;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryCommandHandler implements Command<CategoryCreationDTO, CategoryDTO> {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CreateCategoryCommandHandler(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO execute(CategoryCreationDTO dto) {
        Category category = categoryRepository.save(categoryMapper.toCategory(dto));
        return categoryMapper.toDto(category);
    }
}
