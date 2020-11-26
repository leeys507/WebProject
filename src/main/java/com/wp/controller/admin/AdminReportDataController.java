package com.wp.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wp.service.report.ReportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AdminReportDataController {
	private final ReportService reportService;
	
    @PostMapping("/yu/admin/deleteReport")
    public String deleteReport(String[] list) {
    	if(list == null)
    		return "삭제 실패";
    	
    	return reportService.deleteReport(list) == true ? "삭제 성공" : "삭제 실패";
    }
}
