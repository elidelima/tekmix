package com.exercise.product.category.query;

import com.exercise.product.Query;
import com.exercise.product.category.CategoryDTO;
import com.exercise.product.category.CategoryMapper;
import com.exercise.product.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCategoriesQuery implements Query<Void, List<CategoryDTO>> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public GetAllCategoriesQuery(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> execute(Void unused) {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }
}
