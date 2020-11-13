package com.wp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class StudentViewController {
	private final StudentInfoService studentInfoService;
	private final HttpSession httpSession;
	@GetMapping(value = "/yu/studentInfo/studentInfo")
	public String openStudentInfoView(Model model) {
		model.addAttribute("count", studentInfoService.getStudentCount());
		model.addAttribute("userSid",httpSession.getAttribute("sid"));
		return "studentInfo/studentInfo";
	}
	
	@GetMapping(value = "/yu/studentInfo/studentRegistration")
	public String openStudentRegisterView(Model model) {
		model.addAttribute("userSid",httpSession.getAttribute("resSid"));
		return "studentInfo/studentRegistration";
	}
}
