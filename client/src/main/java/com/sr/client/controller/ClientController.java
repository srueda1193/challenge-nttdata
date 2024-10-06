package com.sr.client.controller;

import java.util.List;
import com.sr.client.services.IClientService;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    private IClientService clientService;


    @GetMapping(path = "")
    @Description("Get all client")
    public ResponseEntity<BaseResponseVo> getClients() {

        try {

            List<ClientVo> clients = this.clientService.findClients();

            if (clients.size()>0) {
                return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseVo.builder().status(HttpStatus.OK.value()).data(clients).build());
            }

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseVo.builder().message("No se encontraron clientes").build());

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseVo.builder().message("Hubo un error al buscar el id ingresado")
                    .build());
        }
    }


    @GetMapping(path = "/{clientId}")
    @Description("Get client by id")
    public ResponseEntity<BaseResponseVo> getClientById(@PathVariable Long clientId) {

        try {

            ClientVo client = this.clientService.findClientById(clientId);

            if (null != client) {
                return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseVo.builder().data(client).build());
            }

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseVo.builder().status(HttpStatus.NOT_FOUND.value())
                    .message("No se encontró el id " + clientId).build());

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseVo.builder().message("Hubo un error al buscar el id "+clientId)
                    .build());
        }
    }

    @PostMapping(path = "")
    @Description("Save a new client")
    public ResponseEntity<BaseResponseVo> createUpdateClient(
        @RequestBody ClientVo client) {
        try {
            return ResponseEntity.ok(BaseResponseVo.builder()
                .data(this.clientService.createClient(client)).build());

        } catch (Exception e) {
            return ResponseEntity.ok(BaseResponseVo.builder()
                .data(this.clientService.createClient(client)).build());
        }
    }

    @PutMapping(path = "")
    @Description("update a client")
    public ResponseEntity<BaseResponseVo> updateClient(
        @RequestBody ClientVo client) throws Exception {
        try {
            return ResponseEntity.ok(BaseResponseVo.builder()
                .data(this.clientService.updateClient(client)).build());

        } catch (Exception e) {
            return ResponseEntity.ok(BaseResponseVo.builder()
                .data(this.clientService.updateClient(client)).build());
        }
    }

    @DeleteMapping(path = "/{clientId}")
    @Description("delete a client")
    public ResponseEntity<BaseResponseVo> deleteClient(
        @PathVariable Long clientId) {

        try {
            this.clientService.deleteClient(clientId);
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponseVo.builder().message("Registro eliminado").build());

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseVo.builder().status(HttpStatus.NOT_FOUND.value())
                    .message("No se pudo eliminar el cliente con el id " + clientId).build());
        }
    }

}
