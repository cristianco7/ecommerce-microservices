package com.cristian.microservices.cart_microservice.cartItem;

import com.cristian.microservices.cart_microservice.cart.Cart;
import com.cristian.microservices.cart_microservice.cart.CartItemRepository;
import com.cristian.microservices.cart_microservice.customer.CustomerClient;
import com.cristian.microservices.cart_microservice.customer.CustomerResponse;
import com.cristian.microservices.cart_microservice.exceptions.CartException;
import com.cristian.microservices.cart_microservice.product.ProductClient;
import com.cristian.microservices.cart_microservice.product.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public String addItemToCart(String customerId, @Valid CartItemRequest cartItemRequest) {
        CustomerResponse customerResponse = customerClient.getCustomerById(customerId)
                .orElseThrow(() -> new CartException("Customer with id " + customerId + "does not exist"));

        ProductResponse productResponse = productClient.getProductById(cartItemRequest.productId())
                .orElseThrow(() -> new CartException("Product with id " + cartItemRequest.productId() + "does not exist"));

        if (productResponse.stock() < cartItemRequest.quantity()) {
            throw new CartException("Product with id " + cartItemRequest.productId() + "does not have enough stock ");
        }

        Cart cart = cartRepository.findByCustomerId(customerResponse.id())
                .orElseGet(() -> Cart.builder()
                        .customerId(customerId)
                        .items(new ArrayList<>())
                        .build());

        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        boolean productExist = cart.getItems().stream()
                .anyMatch(item -> item.getProductId().equals(cartItemRequest.productId()));

        if (productExist) {
            throw new CartException("Product with id " + cartItemRequest.productId() + "is already in the cart");

        }
        cart.getItems().add(
                CartItem.builder()
                        .productId(cartItemRequest.productId())
                        .quantity(cartItemRequest.quantity())
                        .build()
        );

        Cart cartSaved = cartRepository.save(cart);

        return cartSaved.getId();

    }
}
