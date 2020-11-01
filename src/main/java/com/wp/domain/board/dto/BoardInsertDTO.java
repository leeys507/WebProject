package com.wp.domain.board.dto;

import com.wp.domain.board.Board;
import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardInsertDTO {
    private String sid;
    private String nickname;
    private String boardtype;
    private String title;
    private String content;
    private String image_path;

    public Board toEntity() {
        Board bData = new Board();
        Student sData = new Student();
        sData.setSid(sid);
        
        bData.setStudentForeignkey(sData);
        bData.setNickname(nickname);
        bData.setBoardtype(boardtype);
        bData.setTitle(title);
        bData.setContent(content);
        bData.setRead_count(0);
        bData.setRegister_date(LocalDateTime.now());
        bData.setImage_path(image_path);
        bData.setLike_count(0);
        return bData;
    }
}