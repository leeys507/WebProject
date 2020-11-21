package com.wp.domain.lecture.dto;

import com.wp.domain.lecture.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class LectureGetDTO {
    private long lno;
    private String sid;
    private int lecturenum;
    private String lecturename;
    private String professor;
    private LocalDateTime register_date;
    private String check_evaluation;
    public LectureGetDTO(Lecture entity){
        this.lno=entity.getLno();
        this.sid=entity.getStudentForeignkey().getSid();
        this.lecturenum=entity.getLecturenum();
        this.lecturename=entity.getLecturename();
        this.professor=entity.getProfessor();
        this.register_date=entity.getRegister_date();
        this.check_evaluation=entity.getCheck_evaluation();
    }
}
