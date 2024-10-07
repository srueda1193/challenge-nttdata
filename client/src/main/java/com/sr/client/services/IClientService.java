package com.sr.client.services;

import java.util.List;
import com.sr.client.entity.ClientEntity;
import com.sr.client.vo.ClientVo;
import org.apache.coyote.BadRequestException;

public interface IClientService {

    List<ClientVo> findClients();

    ClientEntity createClient(ClientVo client);

    ClientEntity updateClient(ClientVo client) throws Exception;

    ClientVo findClientById(Long id);

    ClientVo findClientByName(String name);

    void deleteClient(Long id) throws BadRequestException;

}
