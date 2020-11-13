package com.wp.controller.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeViewController {
	private final HttpSession httpSession;
	
    @GetMapping("/yuhome/index")    // view
    public String openHomeIndexView(Model model) {
		model.addAttribute("userSid",httpSession.getAttribute("sid"));
        return "home/index";
    }
    
	@GetMapping(value = "/yuhome/login")
	public String openHomeLoginView(Model model) {
		model.addAttribute("userSid",httpSession.getAttribute("resSid"));
		return "home/login";
	}
	@GetMapping("/indexLogin")
	public String openIndexLogin() {
		return "home/indexLogin";
	}
}
