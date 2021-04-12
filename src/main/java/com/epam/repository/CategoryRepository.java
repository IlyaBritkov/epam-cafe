package com.epam.repository;

import com.epam.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends Repository<Category, Long> {

    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(Long id);

    List<Category> findAllByProductId(Long productId);

    @Override
    Category add(Category category);

    @Override
    Category update(Category category);

    @Override
    void removeById(Long id);

    @Override
    void remove(Category entity);
}
