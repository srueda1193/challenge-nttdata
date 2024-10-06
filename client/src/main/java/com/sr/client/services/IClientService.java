package com.sr.client.services;

import java.util.List;
import com.sr.client.entity.ClientEntity;
import com.sr.client.vo.ClientVo;

public interface ClientServiceInterface {

    List<ClientEntity> findClients(List<Long> ids);

    ClientEntity createClient(ClientVo client);

    ClientEntity updateClient(ClientVo client);

    ClientEntity findClientById(Long id);

    void deleteClient(Long id);

}
