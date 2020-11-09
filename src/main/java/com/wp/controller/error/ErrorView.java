package com.wp.controller.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ErrorView implements ErrorController {
	private final HttpSession httpSession;
	
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		model.addAttribute("userSid", httpSession.getAttribute("sid"));
		
		if(status != null) {
			int statusCode = Integer.valueOf(status.toString());
		
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "errors/errorPage";
			}
			if(statusCode == HttpStatus.FORBIDDEN.value()) {
				return "errors/errorPage"; 
			} 
		}
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
