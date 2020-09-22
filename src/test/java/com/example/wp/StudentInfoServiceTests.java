//package com.example.wp;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//
//import com.wp.WebProjectApplication;
//import com.wp.domain.student.Student;
//import com.wp.domain.student.dto.StudentInsertDTO;
//import com.wp.service.student.StudentInfoServiceImpl;
//
//@SpringBootTest
//@ContextConfiguration(classes = WebProjectApplication.class)
//public class StudentInfoServiceTests {
//	@Autowired
//	private StudentInfoServiceImpl studentInfoService;
//	
//	@Test
//	public void testServiceOfGetStudent() {
//		Student s = new Student();
//		s.builder()
//		.sid("111")
//		.nickname("아무닉네임")
//		.email("111@gmail.com")
//		.department("아무학과")
//		.syear(2)
//		.gen("M")
//		.build();
//
//		
//		result = studentInfoService.registerStudent(s);
//		System.out.println(result);
//		data = studentInfoService.getStudent("111");
//		System.out.println(data.getEmail());
//	}
//}
