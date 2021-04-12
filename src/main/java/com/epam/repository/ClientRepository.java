package com.epam.repository;

import com.epam.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends Repository<Client, Long> {

    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findById(Long id);

    @Override
    Client add(Client client);

    @Override
    Client update(Client client);

    @Override
    void removeById(Long id);

    @Override
    void remove(Client client);
}
