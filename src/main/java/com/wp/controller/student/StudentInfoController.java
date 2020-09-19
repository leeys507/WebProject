package com.wp.controller.student;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;
import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudentInfoController {
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
	
	@PostMapping("/getStudentInfo")
	public StudentGetDTO getStudentInfo(@RequestBody String sid) throws Exception {
		StudentGetDTO data;
		System.out.println(sid);
		data = studentInfoService.getStudent(sid);
		if (data == null) {
			// TODO => 검색 실패 메시지 전달
			return null;
		}
		System.out.println(data.getEmail());
		return data;
	}
	
	@PostMapping("/studentRegistration")
	public boolean registerStudent(@ModelAttribute StudentInsertDTO data) throws Exception {
		System.out.println(data.getSid() + " " + data.getDepartment() + " " + data.getGen());
		return studentInfoService.registerStudent(data);
	}
	
	@PostMapping("/getStudentCount")
	public int getStudentCount() throws Exception {
		return studentInfoService.getStudentCount();
	}
	
//	@RequestMapping(value = "/studentInfo/studentInfoTest2", method = RequestMethod.POST)
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
}
