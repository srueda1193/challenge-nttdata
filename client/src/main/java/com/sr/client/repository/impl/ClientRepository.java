package com.sr.client.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ClientRepository {


    @PersistenceContext
    private EntityManager entityManager;


//    @Override
//    public ClientEntity findClientById(Long id) {
//        return entityManager.createQuery("from Cliente", ClientEntity.class).getSingleResult();
//    }
//
//    @Override
//    public ClientEntity createClient(ClientEntity clientEntity) {
//        entityManager.persist(clientEntity);
//        return clientEntity;
//    }
//
//    @Override
//    public ClientEntity updateClient(ClientEntity clientEntity) {
//        return null;
//    }
//
//    @Override
//    public void deleteClient(String id) {
//
//    }
}
