package com.example.wp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import com.wp.WebProjectApplication;
import com.wp.domain.student.dto.StudentInsertDTO;
import com.wp.service.student.StudentInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebProjectApplication.class)
public class StudentInsertTests {
	@Autowired
	private StudentInfoService studentInfoService;

	@Test
	public void testOfInsert() {
		StudentInsertDTO data = new StudentInsertDTO();
		data.setSid("111");
		data.setNickname("아무닉네임");
		data.setEmail("111@gmail.com");
		data.setDepartment("아무학과");
		data.setSyear(2);
		data.setGen("M");

		boolean result = studentInfoService.registerStudent(data);
		System.out.println(result);
	}
}