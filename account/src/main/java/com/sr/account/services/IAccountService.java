package com.sr.account.services;

import java.util.List;
import com.sr.account.entity.AccountEntity;
import com.sr.account.vo.AccountVo;

/**
 * Accoutn Service Interface
 */
public interface IAccountService {

    List<AccountVo> findAccounts();

    AccountVo createAccount(AccountVo account) throws Exception;

    List<AccountVo> createAccounts(List<AccountVo> accounts) throws Exception;

    AccountVo updateAccount(AccountVo account) throws Exception;

    AccountVo findAccountById(String accountNumber) throws Exception;

    void deleteAccount(String id) throws Exception;

}
