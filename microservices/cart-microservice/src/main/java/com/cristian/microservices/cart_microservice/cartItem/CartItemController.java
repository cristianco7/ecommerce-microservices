package com.cristian.microservices.cart_microservice.cartItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{customerId}/cart/items")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<String> addItemToCart(@PathVariable String customerId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        return ResponseEntity.ok(cartItemService.addItemToCart(customerId, cartItemRequest));
    }


}
