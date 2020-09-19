package com.wp.domain.board.dto;

import com.wp.domain.board.Board;
import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BoardInsertDTO {
	private String sid;
	private String nickname;
    private String boardtype;
    private String title;
    private String content;
    private int readcount;
    private String imagepath;
    private int likecount;
    
    public Board toEntity() {
    	Board bData = new Board();
    	Student sData = new Student();
    	sData.setSid(sid);
    	sData.setNickname(nickname);
    	
    	bData.setForeignkey(sData);
    	bData.setBoardtype(boardtype);
    	bData.setTitle(title);
    	bData.setContent(content);
    	bData.setReadcount(readcount);
    	bData.setImagepath(imagepath);
    	bData.setLikecount(likecount);
    	return bData;
    }
}
