package com.wp.domain.lectureevaluation.dto;

import com.wp.domain.lectureevaluation.LectureEvaluation;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LectureEvaluationGetDTO {
    private long lno;
    private String nickname;
    private int lecturenum;
    private String lecturename;
    private int star;
    private String professor;
    private String content;
    private String register_date;

    public LectureEvaluationGetDTO(LectureEvaluation entity){
        this.lno=entity.getLno();
        this.nickname=entity.getNickname();
        this.lecturenum=entity.getLecturenum();
        this.lecturename=entity.getLecturename();
        this.star=entity.getStar();
        this.professor=entity.getProfessor();
        this.content=entity.getContent();
        this.register_date=entity.getRegister_date();
    }
}
