package com.wp.domain.student.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentGetMyCommentDTO {
	String content;
	long bno;
	LocalDateTime register_date;
	String boardtype;
	
	public String getRegister_date() {
	   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
	   	return formatter.format(this.register_date);
	}
}
