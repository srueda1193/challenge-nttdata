package com.sr.account.services;

import com.sr.account.vo.ReportVo;

/**
 * Interface for Report Service
 * @author srueda
 */
public interface IReportService {

    /**
     *
     * @param initDate
     * @param endDate
     * @param clientId
     * @return
     * @throws Exception
     */
    ReportVo generateReport(String initDate, String endDate, Long clientId) throws Exception;

}
