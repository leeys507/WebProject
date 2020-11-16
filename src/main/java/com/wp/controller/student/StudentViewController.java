package com.wp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentGetMyBoardDTO;
import com.wp.domain.student.dto.StudentGetMyCommentDTO;
import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class StudentViewController {
	private final HttpSession httpSession;
	private final StudentInfoService studentService;
	
	@GetMapping(value = "/yu/studentInfo/studentInfo")
	public String openStudentInfoView(Model model) {
		StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
		List<StudentGetMyBoardDTO> boardList = null;
		List<StudentGetMyCommentDTO> commentList = null;
		int limit = 5;
		
		if(data != null) {
			boardList = studentService.getMyBoard(data.getSid(), limit);
			commentList = studentService.getMyComment(data.getSid(), limit);
		}

		model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
		model.addAttribute("myBoardList", boardList);
		model.addAttribute("myCommentList", commentList);

		return "studentInfo/studentInfo";
	}
	
	@GetMapping(value = "/yu/studentInfo/studentRegistration")
	public String openStudentRegisterView(Model model) {
		model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
		model.addAttribute("userSid",httpSession.getAttribute("resSid"));
		return "studentInfo/studentRegistration";
	}
}
