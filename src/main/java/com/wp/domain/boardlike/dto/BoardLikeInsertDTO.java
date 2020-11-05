package com.wp.domain.boardlike.dto;

import com.wp.domain.board.Board;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.boardlike.BoardLike;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardLikeInsertDTO {
    private long bno;
    private String sid;
    public BoardLike toEntity() {
        BoardLike blData = new BoardLike();
        Board bData = new Board();
        Student sData = new Student();
        bData.setBno(bno);
        sData.setSid(sid);
        blData.setBoardForeignkey(bData);
        blData.setStudentForeignkey(sData);
        blData.setCheck_like("T");
        return blData;
    }
}
