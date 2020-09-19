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
		if(entity != null)
			System.out.println(entity.getEmail());
		return (entity == null) ? null : new StudentGetDTO(entity);
	}
	

	public boolean updateStudentNickName(String sid, String nickname) {return true;}
//		int result = 0;
//		StudentInsertDTO data = getStudent(sid);
//		
//		//data.Nickname(nickname);
//		
//		result = studentMapper.updateStudentNickName(data);
//		return (result == 1) ? true : false;
//	}
//	

	public boolean updateStudentEmail(String sid, String email) {return true;}
//		int result = 0;
//		StudentInsertDTO data = getStudent(sid);
//		
//		data.setEmail(email);
//		result = studentMapper.updateStudentEmail(data);
//		return (result == 1) ? true : false;
//	}
	
	public int getStudentCount() {
		return (int) studentRepository.count();
	}
}
