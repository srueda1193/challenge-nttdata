package com.sr.account.services;

import java.time.LocalDateTime;
import java.util.List;
import com.sr.account.entity.TransactionEntity;
import com.sr.account.vo.TransactionVo;

public interface ITransactionService {

    TransactionVo findTransactionById(String transactionId) throws Exception;

    List<TransactionVo> findTransactionByRange(LocalDateTime initDate, LocalDateTime endDate,
        String accountNumber)
        throws Exception;

    TransactionEntity createTransaction(TransactionVo transactionVo) throws Exception;

    List<TransactionEntity> createTransactions(List<TransactionVo> transactionVoList)
        throws Exception;

}
