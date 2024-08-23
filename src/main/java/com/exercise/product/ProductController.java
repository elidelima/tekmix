package com.exercise.product;

import com.exercise.product.command.CreateProductCommandHandler;
import com.exercise.product.model.ProductCreationDTO;
import com.exercise.product.model.ProductDTO;
import com.exercise.product.query.GetAllProductsQuery;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private final CreateProductCommandHandler createProductCommandHandler;
    private final GetAllProductsQuery getAllProductsQuery;

    public ProductController(CreateProductCommandHandler createProductCommandHandler, GetAllProductsQuery getAllProductsQuery) {
        this.createProductCommandHandler = createProductCommandHandler;
        this.getAllProductsQuery = getAllProductsQuery;
    }

    @GetMapping("/sanity-checks")
    public String sanityCheck() {
        return "Eli products";
    }

    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody ProductCreationDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().toString());
            List<String> errorMessages = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Long id = createProductCommandHandler.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(getAllProductsQuery.execute(null));
    }

}
