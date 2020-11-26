package com.wp.service.report;

import com.wp.domain.report.Report;
import com.wp.domain.report.dto.ReportInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {
	boolean existMyReportThisType(String sid, String typename, long typenumber);
	boolean insertReport(ReportInsertDTO data);

	Page<Report> findReport(Pageable pageable);
	boolean deleteReport(String[] list);
}
