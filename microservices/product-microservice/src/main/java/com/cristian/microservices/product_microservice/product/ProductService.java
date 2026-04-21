package com.cristian.microservices.product_microservice.product;

import com.cristian.microservices.product_microservice.category.Category;
import com.cristian.microservices.product_microservice.category.CategoryRepository;
import com.cristian.microservices.product_microservice.exceptions.CategoryNotFoundException;
import com.cristian.microservices.product_microservice.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public List<ProductResponse> getAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public Integer createProduct(ProductRequest request) {
        var productToCreate = mapper.toProduct(request);
        productToCreate.setId(null);
        var product = repository.save(productToCreate);
        return product.getId();
    }

    public void updateProduct(ProductRequest request) {
        if (request.id() == null) {
            throw new IllegalArgumentException("Product id is required for update");
        }

        Product product = repository.findById(request.id())
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with id %s not found", request.id())));

        Category category = categoryRepository.findById(request.id())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with id %s not found", request.categoryId())));

        product.setDescription(request.description());
        product.setImageUrl(request.imageUrl());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(category);
    }

    public void deleteProduct(Integer productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product id is required for delete");
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with id %s not found", productId)));

        repository.delete(product);
    }

    public ProductResponse getProductById(Integer productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product id is required");
        }
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with id %s not found", productId)));

    }

}
