package com.wp.domain.matchingcomment.dto;

import com.wp.domain.board.Board;
import com.wp.domain.matching.Matching;
import com.wp.domain.matchingcomment.MatchingComment;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MatchingCommentInsertDTO {
    private long bno;
    private String sid;
    private String nickname;
    private String content;
    public MatchingComment toEntity() {
        MatchingComment mcData = new MatchingComment();
        Matching mData = new Matching();
        Student sData = new Student();

        mData.setBno(bno);
        sData.setSid(sid);

        mcData.setMatchingForeignkey(mData);
        mcData.setStudentForeignkey(sData);
        mcData.setNickname(nickname);
        mcData.setContent(content);
        mcData.setRegister_date(LocalDateTime.now());
        mcData.setCheck_delete("F");
        return mcData;
    }
}
