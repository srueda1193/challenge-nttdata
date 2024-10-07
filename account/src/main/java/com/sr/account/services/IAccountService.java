package com.sr.account.services;

import java.util.List;
import com.sr.account.entity.AccountEntity;
import com.sr.account.vo.AccountVo;

/**
 * Accoutn Service Interface
 */
public interface IAccountService {

    /**
     *
     * @return
     */
    List<AccountVo> findAccounts();

    /**
     *
     * @param account
     * @return
     * @throws Exception
     */
    AccountVo createAccount(AccountVo account) throws Exception;

    /**
     *
     * @param accounts
     * @return
     * @throws Exception
     */
    List<AccountVo> createAccounts(List<AccountVo> accounts) throws Exception;

    /**
     *
     * @param account
     * @return
     * @throws Exception
     */
    AccountVo updateAccount(AccountVo account) throws Exception;

    /**
     *
     * @param accountNumber
     * @return
     * @throws Exception
     */
    AccountVo findAccountById(String accountNumber) throws Exception;

    /**
     *
     * @param clientId
     * @return
     * @throws Exception
     */
    List<AccountVo> findAccountByClientId(Long clientId) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    void deleteAccount(String id) throws Exception;

}
