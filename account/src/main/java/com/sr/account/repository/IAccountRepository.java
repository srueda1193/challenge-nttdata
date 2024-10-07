package com.sr.account.repository;

import java.util.UUID;
import com.sr.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity findAccountByAccountNumber(String accountNumber);

}
