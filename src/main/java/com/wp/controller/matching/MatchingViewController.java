package com.wp.controller.matching;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wp.domain.matching.Matching;
import com.wp.service.matching.MatchingService;
import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MatchingViewController {
	private final StudentInfoService studentInfoService;
	private final MatchingService matchingService;
	private final HttpSession httpSession;
	
    @GetMapping("/matching/matchingList")    // view
    public String openBoardListView(@RequestParam String boardtype, Model model, Pageable pageable) {
        Page<Matching> page = matchingService.findMatchingList(pageable, boardtype);
        model.addAttribute("boardType", boardtype);
        model.addAttribute("matchingList", page);
        model.addAttribute("userSid",httpSession.getAttribute("sid"));
        return "matching/matchingList";
    }
}
