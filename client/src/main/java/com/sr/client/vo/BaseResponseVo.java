package com.sr.client.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BaseResponseVo<T> {

    private Metadata metadata;
    private T data;

}
