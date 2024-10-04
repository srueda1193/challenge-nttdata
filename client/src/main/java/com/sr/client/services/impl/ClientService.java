package com.sr.client.services.impl;

import java.util.List;
import java.util.UUID;
import com.sr.client.entity.ClientEntity;
import com.sr.client.repository.ClientRepositoryInterface;
import com.sr.client.services.ClientServiceInterface;
import com.sr.client.vo.ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements ClientServiceInterface {


    @Autowired
    @Lazy
    ClientRepositoryInterface clientRepository;

    @Override
    public List<ClientEntity> findClients(String id) {
        return null;
    }

    @Override
    public ClientEntity createUpdateClient(ClientVo client) {

        //TODO: validadores por si existe

        //TODO:
        ClientEntity clientEntity = createEntityFromVo(client);
        clientEntity.setClientId(UUID.randomUUID().toString());

        return clientRepository.createClient(clientEntity);
    }

    @Override
    public ClientEntity findClientById(String id) {
        return clientRepository.findClientById(id);
    }

    @Override
    public void deleteClient(String id) {

    }


    private ClientEntity createEntityFromVo(ClientVo clientVo){
        ClientEntity client = new ClientEntity();

        client.setPassword(clientVo.getPassword());
        client.setStatus(clientVo.getStatus());
//        client.setPerson(client.getPerson());

        return client;
    }

}
