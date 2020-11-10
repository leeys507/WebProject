package com.wp.controller.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wp.domain.board.Board;
import com.wp.service.board.BoardService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class SearchViewController {
	private final BoardService boardService;
	private final HttpSession httpSession;
	
    @GetMapping("/search/searchAllList")
    public String searchBoardListView(@RequestParam String text, @RequestParam String date, @RequestParam String option, 
    		Model model, Pageable pageable) {
        Page<Board> page = boardService.searchAll(pageable, text, date, option);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("userSid", httpSession.getAttribute("sid"));
        model.addAttribute("board", page);
        return "search/searchAllList";
    }
}
