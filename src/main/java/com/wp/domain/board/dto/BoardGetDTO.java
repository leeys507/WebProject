package com.wp.domain.board.dto;

import com.wp.domain.board.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardGetDTO {
	private long bno;
	private String sid;
	private String nickname;
	private String boardtype;
	private String title;
	private String content;
	private String register_date;
	private int readcount;
	private int likecount;
	public BoardGetDTO(Board entity) {	
		this.bno = entity.getBno();
        this.sid = entity.getStudentForeignkey().getSid();
        this.nickname = entity.getNickname();
        this.boardtype = entity.getBoardtype();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.register_date = entity.getRegister_date();
        this.readcount = entity.getRead_count();
        this.likecount = entity.getLike_count();
	}
}