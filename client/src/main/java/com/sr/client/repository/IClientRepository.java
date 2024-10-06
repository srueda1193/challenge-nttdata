package com.sr.client.repository;

import com.sr.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryInterface extends JpaRepository<ClientEntity, Long> {

//    ClientEntity findClientById(Long id);

//    ClientEntity createClient(ClientEntity clientEntity);

//    ClientEntity updateClient(ClientEntity clientEntity);

//    void deleteClient(String id);

}
