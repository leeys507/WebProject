package com.wp.domain.lecture.dto;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LectureInsertDTO {
    private String sid;
    private int lecturenum;
    private String lecturename;
    private String professor;
    public Lecture toEntity(){
        Lecture lData = new Lecture();
        Student sData = new Student();
        sData.setSid(sid);
        lData.setStudentForeignkey(sData);
        lData.setLecturename(lecturename);
        lData.setLecturenum(lecturenum);
        lData.setProfessor(professor);
        lData.setCheck_evaluation("F");
        lData.setRegister_date(LocalDateTime.now());
        return lData;
    }
}
