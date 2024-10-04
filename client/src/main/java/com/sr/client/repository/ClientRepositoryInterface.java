package com.sr.client.repository;

import com.sr.client.entity.ClientEntity;

public interface ClientRepositoryInterface {

    ClientEntity findClientById(String id);

    ClientEntity createClient(ClientEntity clientEntity);

    ClientEntity updateClient(ClientEntity clientEntity);

    void deleteClient(String id);

}
