package com.wp.domain.boardcomment.dto;

import com.wp.domain.boardcomment.BoardComment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardCommentGetDTO {
	private long bno;
	private long cno;
	private long ccno;
	private String sid;
	private String nickname;
	private String content;
	private String register_date;
	private String update_time;
	
	public BoardCommentGetDTO(BoardComment entity) {
		this.bno = entity.getBoardForeignkey().getBno();
		this.cno = entity.getCno();
		this.ccno = entity.getCcno();
		this.sid = entity.getStudentForeignkey().getSid();
		this.nickname = entity.getNickname();
		this.content = entity.getContent();
		this.register_date = entity.getRegister_date();
		this.update_time = entity.getUpdate_time();
	}
}