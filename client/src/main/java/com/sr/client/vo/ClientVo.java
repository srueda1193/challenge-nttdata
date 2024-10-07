package com.sr.client.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * ClientVo
 * @author srueda
 */
@Data
public class ClientVo {

    @NotBlank(message = "client id is required")
    private Long clientId;
    @NotBlank(message = "identification is required")
    private String identification;
    @NotBlank(message = "Name is required")
    private String name;
    private String gender;
    @NotBlank(message = "Age is required")
    private Integer age;
    private String address;
    private String phone;
    private String password;
    private Boolean status;

}
