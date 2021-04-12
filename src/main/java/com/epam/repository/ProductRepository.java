package com.epam.repository;

import com.epam.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    @Override
    Product add(Product product);

    @Override
    Product update(Product product);

    @Override
    void removeById(Long id);

    @Override
    void remove(Product product);
}
