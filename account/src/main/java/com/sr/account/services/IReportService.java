package com.sr.account.services;

import com.sr.account.vo.ReportVo;

public interface IReportService {

    ReportVo generateReport(String initDate, String endDate, Long clientId) throws Exception;

}
