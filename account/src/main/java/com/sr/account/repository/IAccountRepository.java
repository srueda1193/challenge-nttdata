package com.sr.account.repository;

import java.util.List;
import java.util.UUID;
import com.sr.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Inteface for AccountRepository
 * @author srueda
 */
public interface IAccountRepository extends JpaRepository<AccountEntity, UUID> {

    /**
     *
     * @param accountNumber
     * @return
     */
    AccountEntity findAccountByAccountNumber(String accountNumber);

    /**
     *
     * @param clientId
     * @return
     */
    List<AccountEntity> findAccountByClientId(Long clientId);

}
