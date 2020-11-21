package com.wp.domain.lectureevaluation.dto;

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
    public LectureEvaluation toEntity(){
        LectureEvaluation leData = new LectureEvaluation();
        Student sData = new Student();
        sData.setSid(sid);
        leData.setStudentForeignkey(sData);
        leData.setNickname(nickname);
        leData.setLecturename(lecturename);
        leData.setLecturenum(lecturenum);
        leData.setProfessor(professor);
        leData.setRegister_date(LocalDateTime.now());
        leData.setCheck_delete("F");
        leData.setStar(0);
        return leData;
    }
}
