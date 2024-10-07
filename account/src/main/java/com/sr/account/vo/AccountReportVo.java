package com.sr.account.vo;

import java.util.List;
import lombok.Data;

@Data
public class AccountReportVo {

    private AccountVo accountVo;
    private List<TransactionVo> transactions;

}
