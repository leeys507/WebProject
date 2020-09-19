package com.wp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StudentViewController {
	private final StudentInfoService studentInfoService;
	
	@GetMapping(value = "/studentInfo/studentInfoTest")	// view
	public String openStudentInfoView(Model model) {
		model.addAttribute("count", studentInfoService.getStudentCount());
		return "studentInfo/studentInfoTest";
	}
	
	@GetMapping(value = "/studentInfo/studentRegistrationTest")
	public String openStudentRegisterView(Model model) {
		return "studentInfo/studentRegistrationTest";
	}
}
