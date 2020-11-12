package com.wp.domain.matching.dto;

import com.wp.domain.board.Board;
import com.wp.domain.matching.Matching;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MatchingInsertDTO {
    private long bno;
    private String sid;
    private String nickname;
    private String boardtype;
    private int money;
    private String title;
    private String content;
    public Matching toEntity() {
        Matching MData = new Matching();
        Student sData = new Student();
        sData.setSid(sid);
        MData.setStudentForeignkey_request(sData);
        MData.setRequest_nickname(nickname);
        MData.setBoardtype(boardtype);
        MData.setMoney(money);
        MData.setTitle(title);
        MData.setContent(content);
        MData.setRegister_date(LocalDateTime.now());
        MData.setCheck_delete("F");
        MData.setCheck_success("F");
        return MData;
    }
}
