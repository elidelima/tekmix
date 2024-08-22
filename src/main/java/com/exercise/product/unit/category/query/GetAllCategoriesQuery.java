package com.exercise.product.unit.category.query;

import com.exercise.product.Query;
import com.exercise.product.unit.category.CategoryDTO;
import com.exercise.product.unit.category.CategoryMapper;
import com.exercise.product.unit.category.CategoryRepository;
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
