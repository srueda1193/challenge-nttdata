package com.sr.account.vo;

import lombok.Data;

@Data
public class ClientResponseVo {

    private Long clientId;
    private String identification;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
    private String password;
    private Boolean status;

}
