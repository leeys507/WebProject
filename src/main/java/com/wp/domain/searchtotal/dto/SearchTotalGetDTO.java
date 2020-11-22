package com.wp.domain.searchtotal.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SearchTotalGetDTO {
	String title;
	String nickname;
	long bno;
	LocalDateTime register_date;
	String type;
	String boardtype;
	
	public String getRegister_date() {
	   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
	   	return formatter.format(this.register_date);
	}
}
