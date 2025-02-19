package com.wp.service.student;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;
import com.wp.domain.student.dto.StudentMyInfoDataGetDTO;
import com.wp.domain.student.dto.StudentGetMyBoardDTO;
import com.wp.domain.student.dto.StudentGetMyCommentDTO;

public interface StudentInfoService {
	public boolean registerStudent(StudentInsertDTO data);
	
	public StudentGetDTO getStudent(String sid);
	public String getNickname(String sid);
	public void certifiedPhoneNumber(String phoneNumber, String cerNum);
	public boolean updateStudentNickName(String sid, String nickname, LocalDateTime date);
	public String getPhoneNum(String sid);
	public boolean updateStudentEmail(String sid, String email);
	public StudentMyInfoDataGetDTO getMyInfoData(String sid);
	public int getEqualNickname(String nickname);
	public List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit);
	public List<StudentGetMyCommentDTO> getMyComment(String sid, int limit);
	public Page<StudentGetMyBoardDTO> getMyAllBoard(String sid, Pageable pageable);
	public Page<StudentGetMyCommentDTO> getMyAllComment(String sid, Pageable pageable);
	public int getStudentCount();

	boolean changeNickname(String nickname, String changeNickname);

    String updateStudentGrade(String sid, int syear);
}
