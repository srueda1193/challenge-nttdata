package com.sr.account.controller;

import java.util.ArrayList;
import java.util.List;
import com.sr.account.connector.ClientConnector;
import com.sr.account.services.IAccountService;
import com.sr.account.services.IReportService;
import com.sr.account.services.ITransactionService;
import com.sr.account.vo.AccountReportVo;
import com.sr.account.vo.AccountVo;
import com.sr.account.vo.BaseClientResponseVo;
import com.sr.account.vo.BaseResponseVo;
import com.sr.account.vo.MetadataVo;
import com.sr.account.vo.ReportVo;
import com.sr.account.vo.TransactionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
@Lazy
@Slf4j
public class ReportController {

    @Autowired
    @Lazy
    IReportService reportService;

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
