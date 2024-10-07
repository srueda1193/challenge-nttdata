package com.sr.account.services.imp;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ReportServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }

//    @Test
//    public void testGetReportByDates() throws Exception {
//        String initDate = "2024-10-07 00:54:49.294992";
//        String endDate = "2024-10-07 00:55:24.718562";
//        String clientId = "12345";
//
//        mockMvc.perform(get("/report")
//                .param("initDate", initDate)
//                .param("endDate", endDate)
//                .param("clientId", clientId))
//            .andExpect(status().isNotFound())
//            .andExpect(jsonPath("$.data").doesNotExist());
////            .andExpect(jsonPath("$.metadata.message").value("Report not found"));
//    }

}