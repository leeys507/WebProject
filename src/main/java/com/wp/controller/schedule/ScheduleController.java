package com.wp.controller.schedule;

import com.wp.domain.schedule.Schedule;
import com.wp.domain.schedule.ScheduleRepository;
import com.wp.domain.student.StudentRepository;
import com.wp.service.Schedule.ScheduleService;
import com.wp.service.Schedule.SeleniumService;
import com.wp.service.board.BoardService;
import com.wp.service.boardcomment.BoardCommentService;
import com.wp.service.boardlike.BoardLikeService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ScheduleController {


    @Autowired
    private ScheduleRepository ScheduleRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    private final HttpSession httpSession;

    @GetMapping(value="/yu/schedule/main")
    public String schedulemain(Model model) {

    	model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
        return "schedule/schedulemain";
    }

    @GetMapping(value="/schedule/contentSearch")
    public String contentSearch(Model model) {

    	model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
        return "schedule/contentSearch";
    }

    @GetMapping(value="/schedule/subject")
    public String subject() {
        return "schedule/subject";
    }
}
