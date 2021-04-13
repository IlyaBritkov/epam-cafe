package com.epam.repository;

import com.epam.entity.Product;
import com.epam.util.Products;
import org.joda.money.Money;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryImplTest {
    private final ProductRepository productRepository = ProductRepositoryImpl.getInstance();

    private int allProductsAmount = 1;

    @Test
    @Order(2)
    void methodFIndAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        allProducts.forEach(System.out::println);
        assertEquals(allProductsAmount, allProducts.size());
    }

    @Test
    @Order(8)
    void methodAdd() {
        int expectedSize = productRepository.findAll().size() + 1;
        productRepository.add(
                new Product("Пюрешка с котлетой", "Не с макаронами",
                        Money.of(Products.getBYN_CURRENCY_UNIT(), 4.32)));

        int actualSize = productRepository.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(9)
    void methodFindByName() {
        int expectedSize = 1;
        int actualSize = productRepository.findByName("Пюрешка с котлетой").size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(10)
    void methodUpdate() {
        Product product = productRepository.findByName("Пюрешка с котлетой").get(0);
        product.setName("Пюрешка с котлетками");

        productRepository.update(product);

        int expectedSize = 0;
        int actualSize = productRepository.findByName("Пюрешка с котлетой").size();
        assertEquals(expectedSize, actualSize);

        expectedSize = 1;
        actualSize = productRepository.findByName("Пюрешка с котлетками").size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(11)
    void methodRemoveProductById() {
        long id = productRepository.findByName("Пюрешка с котлетками").get(0).getId();
        int expectedSize = productRepository.findAll().size() - 1;

        productRepository.removeById(id);

        int actualSize = productRepository.findAll().size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void methodGetInstanceShouldReturnSingleton() {
        assertSame(productRepository, ProductRepositoryImpl.getInstance());
    }

    @Test
    void methodFindProductById() {
        long productId = 1;
        Optional<Product> productById = productRepository.findById(productId);
        assertEquals(productById.get().getName(), "Доширак");
    }

    @Test
    void methodRemove() {
        productRepository.add(new Product("Тестовая пицца", "ДодоЛисицца",
                Money.of(Products.getBYN_CURRENCY_UNIT(), 9.58)));
        Product product = productRepository.findByName("Тестовая пицца").get(0);

        int expectedSize = productRepository.findAll().size() - 1;
        productRepository.remove(product);
        int actualSize = productRepository.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

}