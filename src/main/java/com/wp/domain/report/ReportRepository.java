package com.wp.domain.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {
	@Query(value = "select count(*) from report where sid = :sid and typename = :typename and typenumber = :typenumber", nativeQuery = true)
    int existMyReportThisType(@Param("sid") String sid, @Param("typename") String typename, @Param("typenumber") long typenumber);
}
