package com.cristian.microservices.product_microservice.category;

import java.util.List;

public record CategoryReponse(
        Integer id,
        String name,
        String description,
        List<ProductResponse> products
) {
}
