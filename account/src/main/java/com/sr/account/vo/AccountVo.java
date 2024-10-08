package com.sr.account.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * @author srueda
 */
@Data
public class AccountVo {

    @NotBlank(message = "Account number is required")
    private String accountNumber;
    private String accountType;
    private double balance;
    private boolean status;
    @NotBlank(message = "client id is required")
    private Long clientId;

}
