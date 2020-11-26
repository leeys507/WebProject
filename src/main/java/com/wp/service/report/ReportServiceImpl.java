package com.wp.service.report;

import com.wp.domain.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wp.domain.report.ReportRepository;
import com.wp.domain.report.dto.ReportInsertDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
	private final ReportRepository reportRepository;
	
	public boolean existMyReportThisType(String sid, String typename, long typenumber) {
		return reportRepository.existMyReportThisType(sid, typename, typenumber) == 1 ? true : false;
	}
	
	public boolean insertReport(ReportInsertDTO data) {
		return reportRepository.save(data.toEntity()) != null;
	}

	public Page<Report> findReport(Pageable pageable) {
		return reportRepository.getAllReport((PageRequest.of(pageable.getPageNumber(), 10, 
				new Sort(Sort.Direction.DESC, "idx"))));
	}
	
	@Transactional
	public boolean deleteReport(String[] list) {
		return reportRepository.deleteReport(list) > 0 ? true : false;
	}
}
