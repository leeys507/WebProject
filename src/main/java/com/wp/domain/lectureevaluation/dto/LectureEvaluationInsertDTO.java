package com.wp.domain.lectureevaluation.dto;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class LectureEvaluationInsertDTO {
    private String sid;
    private String nickname;
    private int lecturenum;
    private String lecturename;
    private String professor;
    private String content;
    private int star;
    public LectureEvaluationInsertDTO(Lecture ldata, String content, int star) {
        this.sid=ldata.getStudentForeignkey().getSid();
        this.nickname=ldata.getStudentForeignkey().getNickname();
        this.lecturename=ldata.getLecturename();
        this.lecturenum=ldata.getLecturenum();
        this.professor=ldata.getProfessor();
        this.content=content;
        this.star=star;
    }

    public LectureEvaluation toEntity(){
        LectureEvaluation leData = new LectureEvaluation();
        Student sData = new Student();
        sData.setSid(sid);
        leData.setStudentForeignkey(sData);
        leData.setNickname(nickname);
        leData.setStar(star);
        leData.setContent(content);
        leData.setRegister_date(LocalDateTime.now());
        leData.setLecturename(lecturename);
        leData.setLecturenum(lecturenum);
        leData.setProfessor(professor);
        leData.setCheck_delete("F");
        return leData;
    }
}
