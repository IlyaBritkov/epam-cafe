package com.epam.repository;

import com.epam.entity.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * Singleton
 **/
@Slf4j
public class ClientRepositoryImpl implements ClientRepository { // todo
    private static ClientRepositoryImpl INSTANCE;

    private ClientRepositoryImpl() {
    }

    public static ClientRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Client add(Client client) {
        return null;
    }

    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void remove(Client client) {

    }
}
