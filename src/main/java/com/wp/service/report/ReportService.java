package com.wp.service.report;

import com.wp.domain.report.dto.ReportInsertDTO;

public interface ReportService {
	boolean exsistMyReportThisType(String sid, String typename, long typenumber);
	boolean insertReport(ReportInsertDTO data);
}
