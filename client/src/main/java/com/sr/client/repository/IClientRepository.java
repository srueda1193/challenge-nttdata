package com.sr.client.repository;

import com.sr.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for Client repository
 * @author srueda
 */
@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    /**
     *
     * @param identification
     * @return
     */
    ClientEntity findClientByIdentification(String identification);

    /**
     *
     * @param name
     * @return
     */
    ClientEntity findClientByName(String name);

}
