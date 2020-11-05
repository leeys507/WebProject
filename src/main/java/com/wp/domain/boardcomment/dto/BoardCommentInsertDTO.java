package com.wp.domain.boardcomment.dto;

import java.time.LocalDateTime;

import com.wp.domain.board.Board;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCommentInsertDTO {
	private long bno;
	private long ccno;
	private long group_id;
    private String sid;
    private String nickname;
    private String content;

    public BoardComment toEntity() {
    	BoardComment bcData = new BoardComment();
        Board bData = new Board();
        Student sData = new Student();
        
        bData.setBno(bno);
        sData.setSid(sid);
        
        bcData.setBoardForeignkey(bData);
        bcData.setCcno(ccno);
        bcData.setGroup_id(group_id);
        bcData.setStudentForeignkey(sData);
        bcData.setNickname(nickname);
        bcData.setContent(content);
        bcData.setRegister_date(LocalDateTime.now());
        bcData.setCheck_delete("F");
        return bcData;
    }
}