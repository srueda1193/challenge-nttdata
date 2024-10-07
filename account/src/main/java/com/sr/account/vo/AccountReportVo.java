package com.sr.account.vo;

import java.util.List;
import lombok.Data;

/**
 * @author srueda
 */
@Data
public class AccountReportVo {

    private AccountVo accountVo;
    private List<TransactionVo> transactions;

}
