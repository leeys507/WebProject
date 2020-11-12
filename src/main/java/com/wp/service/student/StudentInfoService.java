package com.wp.service.student;


import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;

public interface StudentInfoService {
	public boolean registerStudent(StudentInsertDTO data);
	
	public StudentGetDTO getStudent(String sid);
	public String getNickname(String sid);
	public void certifiedPhoneNumber(String phoneNumber, String cerNum);
	public boolean updateStudentNickName(String sid, String nickname);
	public String getPhoneNum(String sid);
	public boolean updateStudentEmail(String sid, String email);
	
	public int getStudentCount();
}
