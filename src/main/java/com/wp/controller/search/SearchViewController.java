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

@RequiredArgsConstructor
@Controller
public class SearchViewController {
	private final BoardService boardService;
	
    @GetMapping("/search/searchAllList")
    public String searchBoardListView(@RequestParam String text, @RequestParam String date, @RequestParam String option, 
    		Model model, Pageable pageable) {
    	System.out.println(text + " " + date + " " + option);
        Page<Board> page = boardService.searchAll(pageable, text, date, option);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("board", page);
        return "search/searchAllList";
    }
}
