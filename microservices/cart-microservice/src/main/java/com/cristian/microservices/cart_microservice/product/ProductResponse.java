package com.cristian.microservices.cart_microservice.product;

import jakarta.validation.constraints.NotNull;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
