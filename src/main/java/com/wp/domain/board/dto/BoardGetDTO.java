package com.wp.domain.board.dto;

import java.time.LocalDateTime;

import com.wp.domain.board.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardGetDTO {
	private int bno;
	private String sid;
	private String nickname;
	private String boardtype;
	private String title;
	private String content;
	private LocalDateTime regdate;
	private int readcount;
	private String imagepath;
	private Integer likecount;
	
	public BoardGetDTO(Board entity) {
		this.bno = entity.getBno();
        this.sid = entity.getForeignkey().getSid();
        this.nickname = entity.getNickname();
        this.boardtype = entity.getBoard_type();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.regdate = entity.getRegister_date();
        this.readcount = entity.getRead_count();
        this.imagepath = entity.getImage_path();
        this.likecount = entity.getLike_count();
	}
}