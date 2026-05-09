package com.cristian.microservices.cart_microservice.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name ="PRODUCT-MICROSERVICE")
public interface ProductClient {
    @GetMapping("api/v1/product/id}")
    Optional<ProductResponse> getProductById(@PathVariable Integer productId);

}
