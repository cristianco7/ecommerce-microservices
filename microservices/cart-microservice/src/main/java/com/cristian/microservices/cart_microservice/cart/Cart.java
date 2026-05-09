package com.cristian.microservices.cart_microservice.cart;

import com.cristian.microservices.cart_microservice.cartItem.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Cart {
    @Id
    private String id;
    private String customerId;
    private List<CartItem> items;

}
