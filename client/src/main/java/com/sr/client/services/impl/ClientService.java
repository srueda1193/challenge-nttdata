package com.sr.client.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.sr.client.entity.ClientEntity;
import com.sr.client.repository.IClientRepository;
import com.sr.client.services.IClientService;
import com.sr.client.vo.ClientVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientService implements IClientService {


    @Autowired
    @Lazy
    IClientRepository clientRepository;

    @Override
    public List<ClientVo> findClients() {

        List<ClientEntity> clientEntities = clientRepository.findAll();

        List<ClientVo> clientVoList = new ArrayList<>();

        if(clientEntities.size() > 0){
            clientEntities.stream().forEach( clientEntity -> {
                clientVoList.add(mapVoFromEntity(clientEntity));
            });
        }

        return clientVoList;
    }

    @Override
    public ClientEntity createClient(ClientVo client) {

        ClientEntity clientEntity = mapEntityFromVo(client);
        return clientRepository.save(clientEntity);
    }

    @Override
    public List<ClientEntity> createClients(List<ClientVo> clients) {

        List<ClientEntity> clientsCreated = new ArrayList<>();
        clients.stream().forEach(clientVo -> {
            clientsCreated.add(createClient( clientVo));
        });
        return clientsCreated;
    }

    @Override
    public ClientEntity updateClient(ClientVo client) throws Exception {

        ClientEntity clientEntity = clientRepository.findClientByIdentification(client.getIdentification());

        if(clientEntity != null){
            ClientEntity clientUpdate = mapEntityFromVo(client);
            clientUpdate.setCode(clientEntity.getCode());
            return clientRepository.save(clientUpdate);
        }

        throw new Exception("No se puede actualizar el usuario");
    }

    @Override
    public ClientVo findClientById(Long id) {

        Optional<ClientEntity> client = clientRepository.findById(id);

        if(client.isPresent()){
            return mapVoFromEntity(client.get());
        }else{
            return null;
        }

    }

    @Override
    public ClientVo findClientByName(String name) {
        ClientEntity client = clientRepository.findClientByName(name);

        if(client !=null){
            return mapVoFromEntity(client);
        }else{
            return null;
        }
    }

    @Override
    public void deleteClient(Long id) throws BadRequestException {
        ClientVo client = findClientById(id);

        if(client != null){
            clientRepository.deleteById(id);
        }else{
            throw new BadRequestException("Client not found");
        }
    }


    private ClientEntity mapEntityFromVo(ClientVo clientVo){
        ClientEntity client = new ClientEntity();

        client.setPassword(clientVo.getPassword());
        client.setStatus(clientVo.getStatus());
        client.setAddress(clientVo.getAddress());
        client.setAge(clientVo.getAge());
        client.setGender(clientVo.getGender());
        client.setIdentification(clientVo.getIdentification());
        client.setName(clientVo.getName());
        client.setPhone(clientVo.getPhone());

        return client;
    }

    private ClientVo mapVoFromEntity(ClientEntity clientEntity){
        ClientVo client = new ClientVo();

        client.setClientId(clientEntity.getCode());
        client.setPassword(clientEntity.getPassword());
        client.setStatus(clientEntity.getStatus());
        client.setAddress(clientEntity.getAddress());
        client.setAge(clientEntity.getAge());
        client.setGender(clientEntity.getGender());
        client.setIdentification(clientEntity.getIdentification());
        client.setName(clientEntity.getName());
        client.setPhone(clientEntity.getPhone());

        return client;
    }

}
