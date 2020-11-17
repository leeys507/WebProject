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
	private final HttpSession httpSession;
	
    @GetMapping("/yu/index")    // view
    public String openHomeIndexView(Model model) {
        return "home/index";
    }
    
	@GetMapping("/yu/login")
	public String openHomeLoginView(Model model) {
		model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
		return "home/login";
	}
	
	@GetMapping("/indexLogin")
	public String openIndexLogin() {
		return "home/indexLogin";
	}
	
	@GetMapping("/yu/home/footer1")
    public String openHomeFooter1View(Model model){
        model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
        return "home/footer1";
    }

    @GetMapping("/yu/home/footer2")
    public String openHomeFooter2View(Model model){
        model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
        return "home/footer2";
    }

    @GetMapping("/yu/home/footer3")
    public String openHomeFooter3View(Model model){
        model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
        return "home/footer3";
    }
}
