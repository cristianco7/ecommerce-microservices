package com.cristian.microservices.cart_microservice.cart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends MongoRepository<Cart,String> {
    Optional<Cart> findByCustomerId(String customerId);
}
