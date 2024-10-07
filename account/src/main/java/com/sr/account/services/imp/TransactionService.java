package com.sr.account.services.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.sr.account.entity.AccountEntity;
import com.sr.account.entity.TransactionEntity;
import com.sr.account.repository.IAccountRepository;
import com.sr.account.repository.ITransactionRepository;
import com.sr.account.services.ITransactionService;
import com.sr.account.vo.TransactionVo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Implementation of Transaction Service
 *
 * @author srueda
 */
@Service
@Transactional
@Slf4j
public class TransactionService implements ITransactionService {

    @Autowired
    @Lazy
    ITransactionRepository transactionRepository;

    @Autowired
    @Lazy
    IAccountRepository accountRepository;

    /**
     * @param transactionId
     * @return
     * @throws Exception
     */
    @Override
    public TransactionVo findTransactionById(String transactionId) throws Exception {
        Optional<TransactionEntity> transaction = transactionRepository.findById(
            UUID.fromString(transactionId));

        return transaction.map(this::mapVoFromEntity).orElse(null);
    }

    /**
     * @param initDate
     * @param endDate
     * @param accountNumber
     * @return
     * @throws Exception
     */
    @Override
    public List<TransactionVo> findTransactionByRange(LocalDateTime initDate, LocalDateTime endDate,
        String accountNumber) throws Exception {
        log.info("Llegue hasta aqui");
        List<TransactionEntity> transactionList = transactionRepository.findTransactionsBetweenAndAccountId(
            initDate, endDate, accountNumber);
        List<TransactionVo> transactionVos = new ArrayList<>();
        transactionList.stream().forEach(transactionEntity -> {
            transactionVos.add(mapVoFromEntity(transactionEntity));
        });
        return transactionVos;
    }

    /**
     * @param transactionVo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public TransactionEntity createTransaction(TransactionVo transactionVo) throws Exception {

        return transactionRepository.save(mapEntityFromVo(transactionVo));
    }

    /**
     * @param transactionVoList
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<TransactionEntity> createTransactions(List<TransactionVo> transactionVoList)
        throws Exception {
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        transactionVoList.stream().forEach(transactionVo -> {
            try {
                transactionEntities.add(createTransaction(transactionVo));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return transactionEntities;
    }

    /**
     * @param transactionVo
     * @return
     * @throws Exception
     */
    private TransactionEntity mapEntityFromVo(TransactionVo transactionVo) throws Exception {
        TransactionEntity transactionEntity = new TransactionEntity();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");

        // Parse a formatted string back to LocalDateTime
        String formattedDate = LocalDateTime.now().format(formatter);
        LocalDateTime parsedDate = LocalDateTime.parse(formattedDate, formatter);

        transactionEntity.setTransactionType(transactionVo.getTransactionType());
        transactionEntity.setTransactionDate(parsedDate);
        transactionEntity.setAmount(transactionVo.getAmount());
        transactionEntity.setBalance(calculateBalance(transactionVo));
        transactionEntity.setAccount(getAccountForTransaction(transactionVo.getAccountId()));

        return transactionEntity;
    }

    /**
     * @param transactionEntity
     * @return
     */
    private TransactionVo mapVoFromEntity(TransactionEntity transactionEntity) {
        TransactionVo transactionVo = new TransactionVo();

        transactionVo.setTransactionDate(transactionEntity.getTransactionDate());
        transactionVo.setTransactionType(transactionEntity.getTransactionType());
        transactionVo.setBalance(transactionEntity.getBalance());
        transactionVo.setAmount(transactionEntity.getAmount());
        transactionVo.setAccountId(transactionEntity.getAccount().getId().toString());

        return transactionVo;
    }

    /**
     * @param balance
     * @param amount
     * @return
     */
    private Double saveDeposit(Double balance, Double amount) {
        return balance + amount;
    }

    /**
     * @param balance
     * @param amount
     * @return
     * @throws Exception
     */
    private Double saveWithdraw(Double balance, Double amount) throws Exception {
        if (Math.abs(amount) > Math.abs(balance)) {
            throw new Exception("Insufficient balance");
        }
        return Math.abs(balance) - Math.abs(amount);
    }

    /**
     * @param transactionVo
     * @return
     * @throws Exception
     */
    private Double calculateBalance(TransactionVo transactionVo) throws Exception {

        try {
            AccountEntity account = getAccountForTransaction(transactionVo.getAccountId());
            Double balanceUpdated = 0.00;

            if (account != null) {
                if (Double.compare(transactionVo.getAmount(), 0) >= 0) {
                    //Credit
                    balanceUpdated = saveDeposit(account.getBalance(), transactionVo.getAmount());
                } else {
                    //Debit
                    balanceUpdated = saveWithdraw(account.getBalance(), transactionVo.getAmount());
                }
                updateBalance(account, balanceUpdated);

            }
            return balanceUpdated;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param account
     * @param balance
     */
    private void updateBalance(AccountEntity account, Double balance) {
        account.setBalance(balance);
        accountRepository.save(account);
    }

    /**
     * @param accountNumber
     * @return
     */
    private AccountEntity getAccountForTransaction(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }
}
