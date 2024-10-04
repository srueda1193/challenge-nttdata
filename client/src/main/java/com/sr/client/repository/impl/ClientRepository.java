package com.sr.client.repository.impl;

import com.sr.client.entity.ClientEntity;
import com.sr.client.repository.ClientRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ClientRepository implements ClientRepositoryInterface {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ClientEntity findClientById(String id) {
        return entityManager.createQuery("from Cliente", ClientEntity.class).getSingleResult();
    }

    @Override
    public ClientEntity createClient(ClientEntity clientEntity) {
        entityManager.persist(clientEntity);
        return clientEntity;
    }

    @Override
    public ClientEntity updateClient(ClientEntity clientEntity) {
        return null;
    }

    @Override
    public void deleteClient(String id) {

    }
}
