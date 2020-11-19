package com.wp.controller.home;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeViewController {
	
    @GetMapping("/yu/index")    // view
    public String openHomeIndexView(Model model) {
        return "home/index";
    }
    
	@GetMapping("/yu/login")
	public String openHomeLoginView(Model model) {
		return "home/login";
	}
	
	@GetMapping("/indexLogin")
	public String openIndexLogin() {
		return "home/indexLogin";
	}
	
	@GetMapping("/yu/home/footer1")
    public String openHomeFooter1View(Model model){
        return "home/footer1";
    }

    @GetMapping("/yu/home/footer2")
    public String openHomeFooter2View(Model model){
        return "home/footer2";
    }

    @GetMapping("/yu/home/footer3")
    public String openHomeFooter3View(Model model){
        return "home/footer3";
    }
}
