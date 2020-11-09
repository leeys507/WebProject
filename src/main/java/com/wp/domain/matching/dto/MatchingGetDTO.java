package com.wp.domain.matching.dto;

import com.wp.domain.matching.Matching;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchingGetDTO {
	private long bno;
	private String request_sid;
	private String request_nickname;
	private String accept_sid;
	private String accept_nickname;
	private String boardtype;
	private int money;
	private String title;
	private String content;
	private String register_date;
	private int readcount;
	private String imagepath;
	private String update_date;
	private String delete_date;
	private String check_delete;
	
	public MatchingGetDTO(Matching entity) {	
		this.bno = entity.getBno();
        this.request_sid = entity.getStudentForeignkey_request().getSid();
        this.request_nickname = entity.getRequest_nickname();
        this.accept_sid = entity.getStudentForeignkey_accept() == null ? null : entity.getStudentForeignkey_accept().getSid();
        this.accept_nickname = entity.getAccept_nickname();
        this.boardtype = entity.getBoardtype();
        this.money = entity.getMoney();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.register_date = entity.getRegister_date();
        this.readcount = entity.getRead_count();
        this.update_date = entity.getUpdate_date();
        this.delete_date = entity.getDelete_date();
        this.check_delete = entity.getCheck_delete();
	}
}
