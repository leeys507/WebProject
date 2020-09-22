package com.wp.domain.student.dto;

import java.time.LocalDateTime;

import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentGetDTO {
	private String sid;
	private String nickname;
	private String gen;
	private String email;
	private int syear;
	private String department;
	private int point;
	private int exp;
	private LocalDateTime first_login;
	private int fboard;
	private LocalDateTime update_time;
	
	public StudentGetDTO(Student entity) {
		this.sid = entity.getSid();
		this.nickname = entity.getNickname();
		this.gen = entity.getGen();
		this.email = entity.getEmail();
		this.syear = entity.getSyear();
		this.department = entity.getDepartment();
		this.point = entity.getPoint();
		this.exp = entity.getExp();
		this.first_login = entity.getFirst_login();
		//this.fboard = entity.getFboard();
		this.update_time = entity.getUpdate_time();
	}
}
