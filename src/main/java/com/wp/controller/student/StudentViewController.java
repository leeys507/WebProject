package com.wp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	private final StudentInfoService studentInfoService;
	
	@GetMapping(value = "/yu/studentInfo/studentInfo")
	public String openStudentInfoView(Model model) {
		StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
		List<StudentGetMyBoardDTO> boardList = null;
		List<StudentGetMyCommentDTO> commentList = null;
		int limit = 5;
		
		if(data != null) {
			boardList = studentInfoService.getMyBoard(data.getSid(), limit);
			commentList = studentInfoService.getMyComment(data.getSid(), limit);
		}

		model.addAttribute("myBoardCount", studentInfoService.getMyBoardCount(data.getSid()));
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
	
	@GetMapping(value = "/yu/studentInfo/studentMyBoardList")
	public String openStudentMyBoardListView(Model model, Pageable pageable) {
		StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
		Page<StudentGetMyBoardDTO> boardList = null;
		
		if(data != null) {
			boardList = studentInfoService.getMyAllBoard(data.getSid(), pageable);
		}
		
		model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
		model.addAttribute("myBoardList", boardList);

		return "studentInfo/studentMyBoardList";
	}
	
	@GetMapping(value = "/yu/studentInfo/studentMyCommentList")
	public String openStudentMyCommentListView(Model model, Pageable pageable) {
		StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
		Page<StudentGetMyCommentDTO> commentList = null;
		
		if(data != null) {
			commentList = studentInfoService.getMyAllComment(data.getSid(), pageable);
		}
		
		model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
		model.addAttribute("myCommentList", commentList);
		
		return "studentInfo/studentMyCommentList";
	}
}
