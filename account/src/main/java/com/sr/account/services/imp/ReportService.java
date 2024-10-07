package com.sr.account.services.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.sr.account.connector.ClientConnector;
import com.sr.account.services.IAccountService;
import com.sr.account.services.IReportService;
import com.sr.account.services.ITransactionService;
import com.sr.account.vo.AccountReportVo;
import com.sr.account.vo.AccountVo;
import com.sr.account.vo.BaseClientResponseVo;
import com.sr.account.vo.ReportVo;
import com.sr.account.vo.TransactionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Implementation of Report Service
 * @author srueda
 */
@Service
@Slf4j
public class ReportService implements IReportService {

    @Autowired
    @Lazy
    ITransactionService transactionService;

    @Autowired
    @Lazy
    IAccountService accountService;

    @Autowired
    @Lazy
    ClientConnector clientConnector;

    /**
     *
     * @param initDate
     * @param endDate
     * @param clientId
     * @return
     * @throws Exception
     */
    @Override
    public ReportVo generateReport(String initDate, String endDate, Long clientId)
        throws Exception {

        try {

            //client
            BaseClientResponseVo clientResponseVo = clientConnector.findClientByClientId(
                clientId);

            //accounts
            List<AccountVo> accounts = accountService.findAccountByClientId(
                clientResponseVo.getData().getClientId());

            List<AccountReportVo> reportAccountList = new ArrayList<>();
            accounts.stream().forEach(accountVo -> {
                AccountReportVo accountReportVo = new AccountReportVo();
                accountReportVo.setAccountVo(accountVo);
                //transactions
                try {
                    accountReportVo.setTransactions(getTransactions(initDate, endDate, accountVo.getAccountNumber()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                reportAccountList.add(accountReportVo);
            });

            ReportVo reportVo = new ReportVo();
            reportVo.setClient(clientResponseVo.getData());
            reportVo.setAccountReportList(reportAccountList);

            return reportVo;
        } catch (Exception e) {
            throw new Exception("Error creating report for client id " + clientId);
        }
    }

    /**
     *
     * @param initDate
     * @param endDate
     * @param accountNumber
     * @return
     * @throws Exception
     */
    private List<TransactionVo> getTransactions(String initDate, String endDate, String accountNumber)
        throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        LocalDateTime initDateLocal = LocalDateTime.parse(initDate, formatter);
        LocalDateTime endDateLocal = LocalDateTime.parse(endDate, formatter);

        log.info(String.valueOf(initDateLocal));
        log.info(String.valueOf(endDate));
        log.info(accountNumber);

        return transactionService.findTransactionByRange(initDateLocal, endDateLocal, accountNumber);
    }
}
