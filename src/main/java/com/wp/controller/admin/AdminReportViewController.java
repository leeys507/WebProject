package com.wp.controller.admin;

import com.wp.domain.report.Report;
import com.wp.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminReportViewController {
    private final ReportService reportService;

    @GetMapping("/yu/admin/reportList")
    public String openAdminReportView(Model model, Pageable pageable) {
        Page<Report> page = reportService.findReport(pageable);
        model.addAttribute("reportList", page);
        return "admin/adminReportList";
    }
}
