package com.epam.repository;

import com.epam.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    User add(User user);

    @Override
    User update(User user);

    @Override
    void removeById(Long id);

    @Override
    void remove(User user);
}
