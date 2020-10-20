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
	private long group_id;
	private String sid;
	private String nickname;
	private String content;
	private String register_date;
	private String update_date;
	private String delete_date;
	private String check_delete;
	
	public BoardCommentGetDTO(BoardComment entity) {
		this.bno = entity.getBoardForeignkey().getBno();
		this.cno = entity.getCno();
		this.ccno = entity.getCcno();
		this.group_id = entity.getGroup_id();
		this.sid = entity.getStudentForeignkey().getSid();
		this.nickname = entity.getNickname();
		this.content = entity.getContent();
		this.register_date = entity.getRegister_date();
		this.update_date = entity.getUpdate_date();
		this.delete_date = entity.getDelete_date();
		this.check_delete = entity.getCheck_delete();
	}
}