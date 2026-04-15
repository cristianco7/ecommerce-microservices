package com.cristian.microservices.product_microservice.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category toCategory(CategoryRequest request) {
        return Category.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();
    }

    public CategoryReponse toCategoryReponse(Category category) {
        return new CategoryReponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getProducts().stream()
                        .map(ProductMapper::toProductReponse)
                        .toList()
        );
    }
}
