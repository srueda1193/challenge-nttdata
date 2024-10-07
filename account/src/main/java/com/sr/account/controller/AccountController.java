package com.sr.account.controller;

import java.util.List;
import com.sr.account.services.IAccountService;
import com.sr.account.vo.AccountVo;
import com.sr.account.vo.BaseResponseVo;
import com.sr.account.vo.MetadataVo;
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
@RequestMapping("/api/v1/account")
@Lazy
@Slf4j
public class AccountController {

    @Lazy
    @Autowired
    private IAccountService accountService;


    @GetMapping(path = "")
    @Description("Get all accounts")
    public ResponseEntity<BaseResponseVo> getAccounts() {

        try {

            List<AccountVo> accounts = this.accountService.findAccounts();

            if (!accounts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseVo.builder()
                        .metadata(MetadataVo.builder()
                            .status(HttpStatus.OK.value()).build())
                        .data(accounts).build());
            }

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .message("No se encontraron clientes").build())
                    .build());

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .message("Hubo un error al buscar el id ingresado").build())
                    .build());
        }
    }


    @GetMapping(path = "/{accountNumber}")
    @Description("Get account by id")
    public ResponseEntity<BaseResponseVo> getAccountById(@PathVariable String accountNumber) {

        try {

            AccountVo account = this.accountService.findAccountById(accountNumber);

            if (null != account) {
                return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseVo.builder()
                        .metadata(MetadataVo.builder()
                            .status(HttpStatus.OK.value())
                            .message("Account Found").build())
                        .data(account).build());
            }

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Account number " + accountNumber + " not found").build())
                    .build());

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .message("There was an error with "+accountNumber + " account number").build())
                    .build());
        }
    }

    @PostMapping(path = "")
    @Description("Save a new account")
    public ResponseEntity<BaseResponseVo> createAccount(
        @RequestBody AccountVo account) throws Exception {
        try {
            return ResponseEntity.ok(BaseResponseVo.builder()
                .data(this.accountService.createAccount(account)).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse(e.getMessage()));
        }
    }

    @PostMapping(path = "/accounts")
    @Description("Save a new account")
    public ResponseEntity<BaseResponseVo> createAccounts(
        @RequestBody List<AccountVo> accounts) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.OK.value())
                        .message("List created").build())
                .data(this.accountService.createAccounts(accounts)).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse(e.getMessage()));
        }
    }

    @PutMapping(path = "")
    @Description("update an account")
    public ResponseEntity<BaseResponseVo> updateAccount(
        @RequestBody AccountVo account) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.OK.value())
                        .message("Account Updated").build())
                    .data(this.accountService.updateAccount(account)).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(notFoundResponse("Account not found"));
        }
    }

    @DeleteMapping(path = "/{accountNumber}")
    @Description("delete an account")
    public ResponseEntity<BaseResponseVo> deleteAccount(
        @PathVariable String accountNumber) {

        try {
            this.accountService.deleteAccount(accountNumber);
            return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Registro eliminado").build())
                    .build());

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(notFoundResponse("No se pudo eliminar el cliente con el id " + accountNumber));
        }
    }

    public BaseResponseVo errorResponse(String message){
        return BaseResponseVo.builder()
            .metadata(MetadataVo.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(message).build())
            .build();
    }

    public BaseResponseVo notFoundResponse(String message){
        return BaseResponseVo.builder()
            .metadata(MetadataVo.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(message).build())
            .build();
    }

}
