package com.sr.account.vo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author srueda
 */
@Data
public class TransactionVo {

    private LocalDateTime transactionDate;
    private String transactionType;
    private double amount;
    private double balance;
    private String accountId;

}
