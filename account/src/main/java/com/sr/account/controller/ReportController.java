package com.sr.account.controller;

import com.sr.account.services.IReportService;
import com.sr.account.vo.BaseResponseVo;
import com.sr.account.vo.MetadataVo;
import com.sr.account.vo.ReportVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for report
 * @author srueda
 */
@RestController
@RequestMapping("/api/v1/report")
@Lazy
@Slf4j
public class ReportController {

    @Autowired
    @Lazy
    IReportService reportService;

    /**
     *
     * @param initDate
     * @param endDate
     * @param clientId
     * @return
     */
    @GetMapping(path = "")
    @Description("Get Report by id")
    public ResponseEntity<BaseResponseVo> getReportByDates(@RequestParam String initDate,
        @RequestParam String endDate, @RequestParam Long clientId) {

        try {

            ReportVo reportVo = reportService.generateReport(initDate, endDate, clientId);

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.OK.value())
                        .message("Report generated").build())
                    .data(reportVo).build());

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseVo.builder()
                    .metadata(MetadataVo.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .build())
                    .build());
        }
    }

}
