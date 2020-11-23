package com.wp.domain.report.dto;

import java.time.LocalDateTime;

import com.wp.domain.report.Report;
import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportInsertDTO {
    private String sid;
    private String reported_title;
    private String typename;
    private long typenumber;
    private String reason;
    private LocalDateTime register_date;
    
    public Report toEntity() {
    	Report rData = new Report();
        Student sData = new Student();
        
        sData.setSid(sid);
        
        rData.setStudentForeignkey(sData);
        rData.setReported_title(reported_title);
        rData.setTypename(typename);
        rData.setTypenumber(typenumber);
        rData.setReason(reason);
        rData.setRegister_date(LocalDateTime.now());
        return rData;
    }
}
