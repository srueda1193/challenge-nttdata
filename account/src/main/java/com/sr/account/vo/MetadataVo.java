package com.sr.account.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author srueda
 */
@Data
@Builder
public class MetadataVo {

    private Integer status;
    private String message;

}
