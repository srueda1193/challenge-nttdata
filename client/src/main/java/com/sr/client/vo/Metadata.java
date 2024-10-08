package com.sr.client.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Metadata
 * @author srueda
 */
@Builder
@Getter
@Setter
public class Metadata {

    private Integer status;
    private String message;

}
