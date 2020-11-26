package com.wp.domain.report;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {
	@Query(value = "select count(*) from report where sid = :sid and typename = :typename and typenumber = :typenumber", nativeQuery = true)
    int existMyReportThisType(@Param("sid") String sid, @Param("typename") String typename, @Param("typenumber") long typenumber);
	
	@Query(value = "select * from report where check_delete = 'F'", nativeQuery = true)
    Page<Report> getAllReport(Pageable page);
	
	@Transactional
	@Modifying
	@Query(value = "update report set check_delete = 'T' where idx in (:idx)", nativeQuery = true)
	int deleteReport(@Param("idx") String[] idxList);
}
