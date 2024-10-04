package com.sr.client.services;

import java.util.List;
import com.sr.client.entity.ClientEntity;
import com.sr.client.vo.ClientVo;

public interface ClientServiceInterface {

    List<ClientEntity> findClients(String id);

    ClientEntity createUpdateClient(ClientVo client);

    ClientEntity findClientById(String id);

    void deleteClient(String id);

}
