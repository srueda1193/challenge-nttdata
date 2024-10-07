package com.sr.account.services.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.sr.account.connector.ClientConnector;
import com.sr.account.entity.AccountEntity;
import com.sr.account.repository.IAccountRepository;
import com.sr.account.services.IAccountService;
import com.sr.account.vo.AccountVo;
import com.sr.account.vo.BaseClientResponseVo;
import com.sr.account.vo.BaseResponseVo;
import com.sr.account.vo.ClientResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

@Service
@Slf4j
public class AccountServices implements IAccountService {

    @Autowired
    @Lazy
    IAccountRepository accountRepository;

    @Autowired
    @Lazy
    ClientConnector clientConnector;

    @Override
    public List<AccountVo> findAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountVo> accountVos = new ArrayList<>();

        accountEntities.stream().forEach(accountEntity -> {
            accountVos.add(mapVoFromEntity(accountEntity));
        });
        return accountVos;
    }

    @Override
    public AccountVo createAccount(AccountVo account) throws Exception {

        try {
            log.info("into del try");
            BaseClientResponseVo responseVo = clientConnector.findClientByClientId(
                account.getClientId());
            log.info("antes del if");


            if (responseVo.getMetadata().getStatus() == 200) {
                log.info("dentro del if");
                AccountEntity accountEntity = mapEntityFromVo(account,
                    responseVo.getData().getClientId());
                return mapVoFromEntity(accountRepository.save(accountEntity));
            } else {
                throw new Exception("Client not found or status not successful.");
            }
        } catch (Exception e) {
            throw new Exception("There was an error creating this account, please check the data",
                e);
        }
    }

    @Override
    public List<AccountVo> createAccounts(List<AccountVo> accounts) throws Exception {
        accounts.stream().forEach(accountVo -> {
            try {
                this.createAccount(accountVo);
            } catch (Exception e) {
                throw new RuntimeException("There was an error creating this list of accounts", e);
            }
        });
        return accounts;
    }


    @Override
    public AccountVo updateAccount(AccountVo account) throws Exception {

        try {
            AccountEntity accountEntity = accountRepository.findAccountByAccountNumber(
                account.getAccountNumber());

            if (accountEntity != null) {
                AccountEntity accountUpdated = mapEntityFromVo(account,
                    accountEntity.getClientId());
                accountUpdated.setId(accountEntity.getId());
                accountRepository.save(accountUpdated);
                return account;
            }
            throw new Exception(
                "Account with account number " + account.getAccountNumber() + " not found ");
        } catch (Exception e) {
            throw new Exception(
                "Something when wrong while updating account " + account.getAccountNumber());
        }

    }

    @Override
    public AccountVo findAccountById(String accountNumber) throws Exception {
        AccountEntity account = accountRepository.findAccountByAccountNumber(accountNumber);

        if (account != null) {
            AccountVo accountVo = mapVoFromEntity(account);
            return accountVo;
        }

        return null;
    }

    @Override
    public List<AccountVo> findAccountByClientId(Long clientId) throws Exception {
        List<AccountEntity> accounts = accountRepository.findAccountByClientId(clientId);
        List<AccountVo> accountVoList = new ArrayList<>();

        if (!accounts.isEmpty()) {
            accounts.forEach(account -> {
                accountVoList.add(mapVoFromEntity(account));
            });
            return accountVoList;
        }
        return accountVoList;
    }

    @Override
    public void deleteAccount(String id) throws Exception {
        log.info("here");

        AccountEntity account = accountRepository.findAccountByAccountNumber(id);

        if (account != null) {
            accountRepository.delete(account);
        } else {
            throw new Exception("Error while trying to delete account with id: " + id);
        }

    }

    private AccountEntity mapEntityFromVo(AccountVo accountVo, Long id) {
        AccountEntity account = new AccountEntity();

        account.setAccountNumber(accountVo.getAccountNumber());
        account.setStatus(accountVo.isStatus());
        account.setAccountType(accountVo.getAccountType());
        account.setBalance(accountVo.getBalance());
        account.setClientId(id);

        return account;
    }

    private AccountVo mapVoFromEntity(AccountEntity accountEntity) {
        AccountVo account = new AccountVo();

        account.setAccountNumber(accountEntity.getAccountNumber());
        account.setStatus(accountEntity.isStatus());
        account.setAccountType(accountEntity.getAccountType());
        account.setBalance(accountEntity.getBalance());
        account.setClientId(accountEntity.getClientId());

        return account;
    }

}
