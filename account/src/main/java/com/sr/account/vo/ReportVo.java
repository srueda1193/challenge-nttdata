package com.sr.account.vo;

import java.util.List;
import lombok.Data;

@Data
public class ReportVo {

    private ClientResponseVo client;
    private List<AccountReportVo> accountReportList;

}
