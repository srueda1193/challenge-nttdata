package com.sr.client.controller;

import com.sr.client.services.ClientServiceInterface;
import com.sr.client.vo.BaseResponseVo;
import com.sr.client.vo.ClientVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
@Lazy
@Slf4j
public class ClientController {

    @Lazy
    @Autowired
    private ClientServiceInterface clientService;

    @GetMapping(path = "/{clientId}")
    @Description("Get client by id")
    public ResponseEntity<BaseResponseVo> getClientById(@PathVariable String clientId) {

        try{
            return ResponseEntity.ok(
                BaseResponseVo.builder().data(this.clientService.findClientById(clientId)).build());
        }catch (Exception e){
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseVo.builder().message("Hubo un error al buscar el id ingresado").build());
        }
    }

    @PostMapping(path = "")
    @Description("Save a new client")
    public ResponseEntity<BaseResponseVo> createUpdateClient(
        @RequestBody ClientVo client) {

        log.info(client.getPerson().toString());
        try {
            return ResponseEntity.ok(BaseResponseVo.builder()
                .data(this.clientService.createUpdateClient(client)).build());

        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseVo.builder()
                .data(this.clientService.createUpdateClient(client)).build());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Description("delete a client")
    public ResponseEntity<BaseResponseVo> deleteClient(
        @RequestBody String clientId) {

        try {
            this.clientService.deleteClient(clientId);
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponseVo.builder().build());

        }catch (Exception e){
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponseVo.builder().build());
        }
    }

}
