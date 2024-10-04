package com.sr.client.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ClientVo {

    PersonRequestVo person;
    private String clientId;
    private String password;
    private Boolean status;

}
