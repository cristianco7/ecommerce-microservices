package com.cristian.microservices.product_microservice.product;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl,
        Integer categoryId
        ) {
}
