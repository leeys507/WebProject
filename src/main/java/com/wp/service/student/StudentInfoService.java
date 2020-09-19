package com.wp.service.student;


import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;

public interface StudentInfoService {
	public boolean registerStudent(StudentInsertDTO data);
	
	public StudentGetDTO getStudent(String sid);
	
	public boolean updateStudentNickName(String sid, String nickname);
	
	public boolean updateStudentEmail(String sid, String email);
	
	public int getStudentCount();
}
