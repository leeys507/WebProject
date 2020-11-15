package com.wp.domain.student.dto;

import java.time.LocalDateTime;

import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter		// necessary
@Getter
@NoArgsConstructor
public class StudentInsertDTO {
	private String sid;
	private String nickname;
    private String gen;
    private String email;
    private int syear;
    private String department;
	private String phonenum;
	
    public Student toEntity() {
    	Student data = new Student();
    	data.setSid(sid);
    	data.setNickname(nickname);
    	data.setGen(gen);
    	data.setEmail(email);
    	data.setSyear(syear);
    	data.setPhonenum(phonenum);
    	data.setDepartment(department);
    	data.setPoint(0);
    	data.setExp(0);
    	data.setFirst_login(LocalDateTime.now());
    	data.setUpdate_date(LocalDateTime.now());
    	return data;
    }
}
