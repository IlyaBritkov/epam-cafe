package com.epam.repository;

import com.epam.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProductRepositoryImplTest {
    private final ProductRepository productRepository = ProductRepositoryImpl.getInstance();
    private int allProductsAmount = 1;

    @Test
    void methodGetInstanceShouldReturnSingleton() {
        assertSame(productRepository, ProductRepositoryImpl.getInstance());
    }

    @Test
    void methodFIndAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        allProducts.forEach(System.out::println);
        assertEquals(allProductsAmount, allProducts.size());
    }

    @Test
    void methodFindProductById() {
        long productId = 1;
        Optional<Product> productById = productRepository.findById(productId);
        assertEquals(productById.get().getName(), "Доширак");
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void delete() {
    }
}