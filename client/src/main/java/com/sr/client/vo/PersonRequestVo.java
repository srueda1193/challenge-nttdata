package com.sr.client.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author srueda
 * PersonRequestVo
 */
@Getter
@Setter
@Builder
@ToString
public class PersonRequestVo {

    private String code;
    private String identification;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;

}
