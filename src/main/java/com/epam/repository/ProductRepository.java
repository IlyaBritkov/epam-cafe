package com.epam.repository;

import com.epam.entity.Category;
import com.epam.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    List<Product> findByName(String name);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByCategory(Category category);

    @Override
    Product add(Product product);

    @Override
    Product update(Product product);

    @Override
    void removeById(Long id);

    @Override
    void remove(Product product);
}
