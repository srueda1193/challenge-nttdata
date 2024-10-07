package com.sr.client.services;

import java.util.List;
import com.sr.client.entity.ClientEntity;
import com.sr.client.vo.ClientVo;
import org.apache.coyote.BadRequestException;

/**
 * Interface fo client service
 * @author srueda
 */
public interface IClientService {

    /**
     *
     * @return
     */
    List<ClientVo> findClients();

    /**
     *
     * @param client
     * @return
     */
    ClientEntity createClient(ClientVo client);

    /**
     *
     * @param client
     * @return
     */
    List<ClientEntity> createClients(List<ClientVo> client);

    /**
     *
     * @param client
     * @return
     * @throws Exception
     */
    ClientEntity updateClient(ClientVo client) throws Exception;

    /**
     *
     * @param id
     * @return
     */
    ClientVo findClientById(Long id);

    /**
     *
     * @param name
     * @return
     */
    ClientVo findClientByName(String name);

    /**
     *
     * @param id
     * @throws BadRequestException
     */
    void deleteClient(Long id) throws BadRequestException;

}
