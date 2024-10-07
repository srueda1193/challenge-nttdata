package com.sr.account.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountVo {

    @NotBlank(message = "Account number is required")
    private String accountNumber;
    private String accountType;
    private double initialBalance;
    private boolean status;
    @NotBlank(message = "client id is required")
    private Long clientId;

}
