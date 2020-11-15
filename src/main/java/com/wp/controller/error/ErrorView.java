package com.wp.controller.error;

import java.util.Date;

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

		model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
		
		model.addAttribute("path", request.getAttribute("javax.servlet.error.request_uri"));
		model.addAttribute("status", status.toString());
		model.addAttribute("timestamp", new Date());
		
		Object exceptionObj = request.getAttribute("javax.servlet.error.exception");
		if(exceptionObj != null) {
			Throwable e = ((Exception)exceptionObj).getCause();
			model.addAttribute("exception", e.getClass().getName());
			model.addAttribute("message", e.getMessage());
		}

		if(status != null) {
			int statusCode = Integer.valueOf(status.toString());
		
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("error", HttpStatus.NOT_FOUND.name());
				return "errors/errorPage";
			}
			if(statusCode == HttpStatus.FORBIDDEN.value()) {
				model.addAttribute("error", HttpStatus.FORBIDDEN.name());
				return "errors/errorPage"; 
			}
			if(statusCode == HttpStatus.BAD_REQUEST.value()) {
				model.addAttribute("error", HttpStatus.BAD_REQUEST.name());
				return "errors/errorPage"; 
			}
			if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("error", HttpStatus.INTERNAL_SERVER_ERROR.name());
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
