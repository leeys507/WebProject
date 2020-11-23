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
    public String InsertReport(@ModelAttribute ReportInsertDTO data) throws Exception {
    	if(reportService.exsistMyReportThisType(data.getSid(), data.getTypename(), data.getTypenumber())) {
    		return "이미 신고가 접수되었습니다";
    	}
        if(reportService.insertReport(data)) {
        	return "신고가 정상적으로 접수되었습니다";
        }
        else {
        	return "신고 접수 실패";
        }
    }
    
    @PostMapping("/yu/exsistMyReportThisType")
    public boolean ExsistMyReportThisType(String sid, String typename, long typenumber) throws Exception {
        return reportService.exsistMyReportThisType(sid, typename, typenumber);
    }
}
