package com.cristian.microservices.product_microservice.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    public List<CategoryReponse> getAllCategories() {
    }

    public Integer createCategory(CategoryRequest request) {
        return null;
    }
}
