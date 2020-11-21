package com.wp.domain.lectureevaluation.dto;

import com.wp.domain.lectureevaluation.LectureEvaluation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class LectureEvaluationGetDTO {
    private String check_delete;
    private String content;
    private LocalDateTime register_date;
    private String lecturename;
    private int lecturenum;
    private long lno;
    private String nickname;
    private String professor;
    private String sid;
    private int star;

    public LectureEvaluationGetDTO(LectureEvaluation entity){
        this.lno=entity.getLno();
        this.sid=entity.getStudentForeignkey().getSid();
        this.nickname=entity.getNickname();
        this.lecturenum=entity.getLecturenum();
        this.lecturename=entity.getLecturename();
        this.content= entity.getContent();
        this.professor=entity.getProfessor();
        this.star=entity.getStar();
        this.check_delete=entity.getCheck_delete();
        this.register_date=entity.getRegister_date();
    }
}
