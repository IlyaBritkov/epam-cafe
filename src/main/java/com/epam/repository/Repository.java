package com.epam.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

    List<T> findAll();

    Optional<T> findById(K id);

    T add(T entity);

    T update(T entity);

    void removeById(K id);

    void remove(T entity);
}
