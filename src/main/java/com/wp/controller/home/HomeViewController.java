package com.wp.controller.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeViewController {
	private final StudentInfoService studentInfoService;
	private final HttpSession httpSession;
	
    @GetMapping("/yu/index")    // view
    public String openHomeIndexView(Model model) {
		model.addAttribute("userSid", httpSession.getAttribute("sid"));
        return "home/index";
    }
    
	@GetMapping("/yu/login")
	public String openHomeLoginView(Model model) {
		String sid = (String)httpSession.getAttribute("sid");
		StudentGetDTO data = studentInfoService.getStudent(sid);
		httpSession.setAttribute("studentInfo", data);
		model.addAttribute("userSid", sid);
		model.addAttribute("studentInfo", data);
		return "home/login";
	}
	
	@GetMapping("/indexLogin")
	public String openIndexLogin() {
		return "home/indexLogin";
	}
}
