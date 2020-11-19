package com.wp.domain.matchingcancel.dto;

import com.wp.domain.matching.Matching;
import com.wp.domain.matchingcancel.MatchingCancel;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class MatchingCancelInsertDTO {

    private long bno;
    private String sid;
    private String content;

    public MatchingCancel toEntity() {
        MatchingCancel mcData = new MatchingCancel();
        Matching mData = new Matching();
        Student sData = new Student();

        mData.setBno(bno);
        sData.setSid(sid);

        mcData.setMatchingForeignkey(mData);
        mcData.setStudentForeignkey(sData);
        mcData.setContent(content);
        mcData.setRegister_date(LocalDateTime.now());
        return mcData;
    }
}
