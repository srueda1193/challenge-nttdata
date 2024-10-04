package com.sr.client.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BaseResponseVo {

    private Integer status;
    private Object data;
    private String message;

}
