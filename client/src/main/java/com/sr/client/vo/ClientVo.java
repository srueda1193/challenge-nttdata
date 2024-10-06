package com.sr.client.vo;

import lombok.Data;

@Data
public class ClientVo {

    private String identification;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
    private String password;
    private Boolean status;

}
