package com.wp.service.student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wp.domain.student.Student;
import com.wp.domain.student.StudentRepository;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	private final StudentRepository studentRepository;
	
	@Transactional
	public boolean registerStudent(StudentInsertDTO data) {
		System.out.println(data.getDepartment());
		return studentRepository.save(data.toEntity()) != null;
	}
	
	public StudentGetDTO getStudent(String sid) {
		Student entity = studentRepository.findBySid(sid);
		return (entity == null) ? null : new StudentGetDTO(entity);
	}
	
	public boolean updateStudentNickName(String sid, String nickname) {
//		Student entity = studentRepository.findBySid(sid);
//		if(entity == null) return false;
//		entity.setNickname(nickname);
//		return studentRepository.save(entity) != null;
		return studentRepository.updateNickname(sid, nickname) > 0 ? true : false;
	}

	public String getNickname(String sid) {
		return studentRepository.getBoardByNickname(sid);
	}
	public boolean updateStudentEmail(String sid, String email) {
		return studentRepository.updateEmail(sid, email) > 0 ? true : false;
	}
	
	public int getStudentCount() {
		return (int) studentRepository.count();
	}
}
