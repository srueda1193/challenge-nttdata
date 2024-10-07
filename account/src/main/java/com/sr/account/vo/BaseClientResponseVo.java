package com.sr.account.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author srueda
 */
@Builder
@Getter
@Setter
public class BaseClientResponseVo {

    private MetadataVo metadata;
    private ClientResponseVo data;

}
