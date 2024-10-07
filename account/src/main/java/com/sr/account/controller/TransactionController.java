package com.sr.account.controller;


import java.util.List;
import com.sr.account.services.ITransactionService;
import com.sr.account.vo.BaseResponseVo;
import com.sr.account.vo.MetadataVo;
import com.sr.account.vo.TransactionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@Lazy
@Slf4j
public class TransactionController {

    @Autowired
    @Lazy
    ITransactionService transactionService;

    @GetMapping(path = "/{transactionId}")
    @Description("Get transaction by id")
    public ResponseEntity<BaseResponseVo> getTransactionById(@PathVariable String transactionId) {

        try {

            TransactionVo transactionVo = this.transactionService.findTransactionById(transactionId);

            if (null != transactionVo) {
                return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseVo.builder()
                        .metadata(MetadataVo.builder()
                            .status(HttpStatus.OK.value())
                            .message("Transaction Found").build())
                        .data(transactionVo).build());
            }

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(notFoundResponse("Transaction id " + transactionId + " not found"));

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .message("There was an error with "+transactionId + " transaction number").build())
                    .build());
        }
    }

    @PostMapping(path = "")
    @Description("Save a new transaction")
    public ResponseEntity<BaseResponseVo> createTransaction(
        @RequestBody TransactionVo transactionVo) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.OK.value())
                        .message("Transaction created").build())
                    .data(this.transactionService.createTransaction(transactionVo)).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse(e.getMessage()));
        }
    }


    @PostMapping(path = "/transactions")
    @Description("Save a new transaction")
    public ResponseEntity<BaseResponseVo> createTransaction(
        @RequestBody List<TransactionVo> transactionVos) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.OK.value())
                        .message("Transactions created").build())
                    .data(this.transactionService.createTransactions(transactionVos)).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse(e.getMessage()));
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
