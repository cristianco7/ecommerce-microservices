package com.cristian.microservices.product_microservice.category;

public record CategoryProductResponse(
        Integer id,
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl
) {
}