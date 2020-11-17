package com.wp.controller.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wp.domain.board.Board;
import com.wp.domain.matching.Matching;
import com.wp.service.board.BoardService;
import com.wp.service.matching.MatchingService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class SearchViewController {
	private final BoardService boardService;
	private final MatchingService matchingService;
	private final HttpSession httpSession;
	
    @GetMapping("/yu/search/searchAllList")
    public String searchBoardListView(@RequestParam String text, @RequestParam String date, @RequestParam String option, 
    		Model model, Pageable pageable) {
        Page<Board> data = boardService.searchAll(pageable, text, date, option);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("board", data);
        model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));

        return "search/searchAllList";
    }
    
    @GetMapping("/yu/search/searchBoardList")
    public String searchBoardListView(@RequestParam String boardtype, @RequestParam String text, 
    		@RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {
        Page<Board> data = boardService.searchBoard(pageable, boardtype, text, date, option);
        
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("board", data);
        model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));

        return "search/searchBoardList";
    }
    
    @GetMapping("/yu/search/searchMatchingList")
    public String searchMatchingListView(@RequestParam String boardtype, @RequestParam String text, 
    		@RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {
        Page<Matching> data = matchingService.searchMatching(pageable, boardtype, text, date, option);
        
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("matchingList", data);
        model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));

        return "search/searchMatchingList";
    }
}
