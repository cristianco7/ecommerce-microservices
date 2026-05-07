package com.cristian.microservices.cart_microservice.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/{customerId}/cart/")
@RequiredArgsConstructor
public class CartController {
}
