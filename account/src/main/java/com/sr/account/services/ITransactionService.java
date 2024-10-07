package com.sr.account.services;

import java.time.LocalDateTime;
import java.util.List;
import com.sr.account.entity.TransactionEntity;
import com.sr.account.vo.TransactionVo;

/**
 * Interface for Transaction Service
 * @author srueda
 */
public interface ITransactionService {

    /**
     *
     * @param transactionId
     * @return
     * @throws Exception
     */
    TransactionVo findTransactionById(String transactionId) throws Exception;

    /**
     *
     * @param initDate
     * @param endDate
     * @param accountNumber
     * @return
     * @throws Exception
     */
    List<TransactionVo> findTransactionByRange(LocalDateTime initDate, LocalDateTime endDate,
        String accountNumber)
        throws Exception;

    /**
     *
     * @param transactionVo
     * @return
     * @throws Exception
     */
    TransactionEntity createTransaction(TransactionVo transactionVo) throws Exception;

    /**
     *
     * @param transactionVoList
     * @return
     * @throws Exception
     */
    List<TransactionEntity> createTransactions(List<TransactionVo> transactionVoList)
        throws Exception;

}
