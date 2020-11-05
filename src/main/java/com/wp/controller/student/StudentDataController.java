package com.wp.controller.student;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;
import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudentDataController {
	private final StudentInfoService studentInfoService;

//	@RequestMapping(value = "/studentInfo/studentInfoTest", method = RequestMethod.POST)
//	public String openBoardWrite(@RequestParam(value = "sid", required = false) String sid, Model model) {
//		model.addAttribute("studentDTO", studentInfoService.getStudent("111"));
//		return "studentInfo/studentInfoTest";
//	}
//	
//	@RequestMapping(value = "/studentInfo/studentInfoTest2", method = RequestMethod.GET)
//	public String getStudentInfo(@RequestParam(value = "sid") String sid, Model model) {
//		StudentDTO data;
//		data = studentInfoService.getStudent(sid);
//		if (data == null) {
//			// TODO => 검색 실패 메시지 전달
//			return "redirect:studentInfo/studentInfoTest";
//		}
//		model.addAttribute("studentDTO", data);
//
//		return "studentInfo/studentInfoTest2";
//	}
	
	@PostMapping("/studentInfo/getStudentInfo")
	public StudentGetDTO getStudentInfo(@RequestBody String sid) throws Exception {
		StudentGetDTO data = studentInfoService.getStudent(sid);
		if (data == null) {
			// TODO => 검색 실패 메시지 전달
			return null;
		}
		System.out.println(data.getEmail());
		System.out.println(data.getFirst_login());
		return data;
	}
	
	@PostMapping("/studentInfo/studentRegistration")
	public boolean registerStudent(@ModelAttribute StudentInsertDTO data) throws Exception {
		System.out.println(data.getSid() + " " + data.getDepartment() + " " + data.getGen());
		return studentInfoService.registerStudent(data);
	}
	
	@PutMapping("/studentInfo/updateStudentNickname")
	public String updateStudentNickname(String sid, String nickname) throws Exception {
		StudentGetDTO data = studentInfoService.getStudent(sid);
//		System.out.println(nickname);
//		LocalDate lastUpdateTime = data.getUpdate_time().toLocalDate();
//		LocalDate currentDate = LocalDate.now();

//		Period period = Period.between(lastUpdateTime, currentDate);
//		if(period.getMonths() < 1) return "닉네임을 재변경하려면 1개월 이상 지나야 합니다.";
		if(data.getNickname().equals(nickname)) return "닉네임이 이전과 같습니다";
		return studentInfoService.updateStudentNickName(sid, nickname) == true ? "닉네임 변경 완료" : "닉네임 변경 실패";
	}
	
	@PutMapping("/studentInfo/updateStudentEmail")
	public boolean updateStudentEmail(String sid, String email) throws Exception {
		System.out.println("email = " + email);
		return studentInfoService.updateStudentEmail(sid, email);
	}
	
	@PostMapping("/studentInfo/getStudentCount")
	public int getStudentCount() throws Exception {
		return studentInfoService.getStudentCount();
	}
}
