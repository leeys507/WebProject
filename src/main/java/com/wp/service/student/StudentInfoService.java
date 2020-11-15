package com.wp.service.student;


import java.util.List;

import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;
import com.wp.domain.student.dto.StudentGetMyBoardDTO;

public interface StudentInfoService {
	public boolean registerStudent(StudentInsertDTO data);
	
	public StudentGetDTO getStudent(String sid);
	public String getNickname(String sid);
	public void certifiedPhoneNumber(String phoneNumber, String cerNum);
	public boolean updateStudentNickName(String sid, String nickname);
	public String getPhoneNum(String sid);
	public boolean updateStudentEmail(String sid, String email);
	public List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit);
	public int getStudentCount();
}
