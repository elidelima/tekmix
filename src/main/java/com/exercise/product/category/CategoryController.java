package com.exercise.product.category;

import com.exercise.product.category.query.GetAllCategoriesQuery;
import com.exercise.product.category.command.CreateCategoryCommandHandler;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final GetAllCategoriesQuery getAllCategoriesQuery;
    private final CreateCategoryCommandHandler createCategoryCommandHandler;

    public CategoryController(GetAllCategoriesQuery getAllCategoriesQuery, CreateCategoryCommandHandler createCategoryCommandHandler) {
        this.getAllCategoriesQuery = getAllCategoriesQuery;
        this.createCategoryCommandHandler = createCategoryCommandHandler;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(getAllCategoriesQuery.execute(null));
    }

    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody CategoryCreationDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        createCategoryCommandHandler.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
