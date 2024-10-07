package com.sr.account.repository;

import java.util.List;
import java.util.UUID;
import com.sr.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity findAccountByAccountNumber(String accountNumber);
    List<AccountEntity> findAccountByClientId(Long clientId);

}
