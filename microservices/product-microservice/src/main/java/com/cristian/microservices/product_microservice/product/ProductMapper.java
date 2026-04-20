package com.cristian.microservices.product_microservice.product;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public static ProductResponse toProductReponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.getCategory().getId(),
                product.getCategory().getDescription(),
                product.getCategory().getName()
        );
    }
}
