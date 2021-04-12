package com.epam.repository;

import com.epam.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/** Singleton **/
@Slf4j
public class UserRepositoryImpl implements UserRepository { // todo

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void remove(User user) {

    }
}
