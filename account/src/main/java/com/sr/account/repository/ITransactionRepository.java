package com.sr.account.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.sr.account.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for Transaction repository
 * @author srueda
 */
public interface ITransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    /**
     *
     * @param initDate
     * @param endDate
     * @param accountNumber
     * @return
     */
    @Query("SELECT t FROM TransactionEntity t WHERE t.transactionDate BETWEEN :initDate AND :endDate AND t.account.accountNumber = :accountNumber")
    List<TransactionEntity> findTransactionsBetweenAndAccountId(LocalDateTime initDate,
        LocalDateTime endDate, String accountNumber);

}
