package com.wp.service.report;

import org.springframework.stereotype.Service;

import com.wp.domain.report.ReportRepository;
import com.wp.domain.report.dto.ReportInsertDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
	private final ReportRepository reportRepository;
	
	public boolean exsistMyReportThisType(String sid, String typename, long typenumber) {
		return reportRepository.exsistMyReportThisType(sid, typename, typenumber) == 1 ? true : false;
	}
	
	public boolean insertReport(ReportInsertDTO data) {
		return reportRepository.save(data.toEntity()) != null;
	}
}
