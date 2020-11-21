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
import com.wp.service.searchword.SearchWordService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class SearchViewController {
	private final BoardService boardService;
	private final MatchingService matchingService;
	
    @GetMapping("/yu/search/searchAllList")
    public String searchAllListView(@RequestParam String text, @RequestParam String date, @RequestParam String type, 
    		@RequestParam String option, Model model, Pageable pageable) {
    	
    	Page<Board> boardList = null;
    	Page<Matching> matchingList = null;
    	
    	text = text.trim().replaceAll(" +", " ");	// remove multiple whitespace
    	
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("type", type);
        model.addAttribute("option", option);
    	
    	if(type.equals("all")) {
    		return "search/searchAllList";
    	}
    	else if(type.equals("board")) {
    		boardList = boardService.searchAll(pageable, text, date, option);
    		model.addAttribute("board", boardList);
    		return "search/searchAllBoardList";
    	}
    	else if(type.equals("matching")) {
    		matchingList = matchingService.searchAll(pageable, text, date, option);
    		model.addAttribute("matchingList", matchingList);
    		return "search/searchAllMatchingList";
    	}
    	return "errors/errorPage";
    }
    
    @GetMapping("/yu/search/searchBoardList")
    public String searchBoardListView(@RequestParam String boardtype, @RequestParam String text, 
    		@RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {
        Page<Board> data = boardService.searchBoard(pageable, boardtype, text, date, option);
        
        text = text.trim().replaceAll(" +", " ");
        
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("board", data);

        return "search/searchBoardList";
    }
    
    @GetMapping("/yu/search/searchMatchingList")
    public String searchMatchingListView(@RequestParam String boardtype, @RequestParam String text, 
    		@RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {
        Page<Matching> data = matchingService.searchMatching(pageable, boardtype, text, date, option);
        
        text = text.trim().replaceAll(" +", " ");
        
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("matchingList", data);

        return "search/searchMatchingList";
    }
}
