package com.wp.controller.report;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.report.dto.ReportInsertDTO;
import com.wp.service.report.ReportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReportDataController {
	private final ReportService reportService;
	
    @PostMapping("/yu/reportInsert")
    public boolean InsertReport(@ModelAttribute ReportInsertDTO data) throws Exception {
        return reportService.insertReport(data);
    }
    
    @PostMapping("/yu/exsistMyReportThisType")
    public boolean ExsistMyReportThisType(String sid, String typename, long typenumber) throws Exception {
        return reportService.exsistMyReportThisType(sid, typename, typenumber);
    }
}
