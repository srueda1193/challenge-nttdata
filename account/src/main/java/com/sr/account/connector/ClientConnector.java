package com.sr.account.connector;

import com.sr.account.vo.BaseClientResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class ClientConnector {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MICROSERVICE_BASE_URL = "http://client-service:8080/api/v1/client";

    public BaseClientResponseVo findClientByClientId(Long clientId){

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(MICROSERVICE_BASE_URL)
            .path("/{clientId}")
            .encode()
            .toUriString();

        log.info(MICROSERVICE_BASE_URL);

        return restTemplate.getForObject(urlTemplate, BaseClientResponseVo.class, clientId);

    }

}
