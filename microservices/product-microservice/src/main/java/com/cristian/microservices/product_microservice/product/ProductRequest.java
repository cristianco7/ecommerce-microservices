package com.cristian.microservices.product_microservice.product;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        String description,
        @NotNull(message = "Price cannot be null")
        Double price,
        Integer stock,
        String imageUrl,
        @NotNull(message = "CategoryId cannot be null")
        Integer categoryId
        ) {
}
