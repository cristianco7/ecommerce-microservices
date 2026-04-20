package com.cristian.microservices.product_microservice.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    public List<CategoryReponse> getAllCategories() {
        return repository.findAll().stream()
                .map(mapper::toCategoryReponse)
                .toList();
    }

    public Integer createCategory(CategoryRequest request) {
        var categoryToCreate = mapper.toCategory(request);
        categoryToCreate.setId(null);
        var category = repository.save(categoryToCreate);
        return category.getId();
    }

    public void updateCategory(@Valid CategoryRequest request) {
     

    }
}
