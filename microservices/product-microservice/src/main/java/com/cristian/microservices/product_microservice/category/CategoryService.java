package com.cristian.microservices.product_microservice.category;

import com.cristian.microservices.product_microservice.exceptions.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    public List<CategoryResponse> getAllCategories() {
        return repository.findAll().stream()
                .map(mapper::toCategoryResponse)
                .toList();
    }

    public Integer createCategory(CategoryRequest request) {
        var categoryToCreate = mapper.toCategory(request);
        categoryToCreate.setId(null);
        var category = repository.save(categoryToCreate);
        return category.getId();
    }

    public void updateCategory(CategoryRequest request) {
        if (request.id() == null) {
            throw new IllegalArgumentException("Category id is required for update");
        }

        Category category  = repository.findById(request.id())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with id %s not found", request.id())));

       category.setName(request.name());
       category.setDescription(request.description());
       repository.save(category);


    }

    public void deleteCategory(Integer categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("categoryId id is required for delete");
        }
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with id %s not found", categoryId)));
        repository.delete(category);
    }

    public CategoryResponse getCategoryById(Integer categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category id is required");
        }

        return repository.findById(categoryId)
                .map(mapper::toCategoryResponse)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with id %s not found", categoryId)));
    }
}
