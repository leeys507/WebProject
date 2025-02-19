package com.wp.controller.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wp.domain.board.Board;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.matching.Matching;
import com.wp.domain.searchtotal.dto.SearchTotalGetDTO;
import com.wp.service.board.BoardService;
import com.wp.service.lectureevaluation.LectureEvaluationService;
import com.wp.service.matching.MatchingService;
import com.wp.service.searchtotal.SearchTotalService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SearchViewController {
	private final BoardService boardService;
	private final MatchingService matchingService;
	private final LectureEvaluationService lectureEvaluationService;
	private final SearchTotalService searchTotalService;
	
    @GetMapping("/yu/search/searchAllList")
    public String searchAllListView(@RequestParam String text, @RequestParam String date, @RequestParam String type, 
    		@RequestParam String option, Model model, Pageable pageable) {	
    	
    	text = text.trim().replaceAll(" +", " ");	// remove multiple whitespace
    	
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("type", type);
        model.addAttribute("option", option);
    	
    	if(type.equals("all")) {
    		Page<SearchTotalGetDTO> searchTotalList = null;
    		searchTotalList = searchTotalService.searchAll(pageable, text, date, option);
    		model.addAttribute("searchTotalList", searchTotalList);
    		return "search/searchAllList";
    	}
    	else if(type.equals("board")) {
    		Page<Board> boardList = null;
    		boardList = boardService.searchAll(pageable, text, date, option);
    		model.addAttribute("board", boardList);
    		return "search/searchAllBoardList";
    	}
    	else if(type.equals("matching")) {
    		Page<Matching> matchingList = null;
    		matchingList = matchingService.searchAll(pageable, text, date, option);
    		model.addAttribute("matchingList", matchingList);
    		return "search/searchAllMatchingList";
    	}
    	return "errors/errorPage";
    }
    
    @GetMapping("/yu/search/searchBoardList")
    public String searchBoardListView(@RequestParam String boardtype, @RequestParam String text, 
    		@RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {
        
        text = text.trim().replaceAll(" +", " ");
        
        Page<Board> data = boardService.searchBoard(pageable, boardtype, text, date, option);
        
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
        
        text = text.trim().replaceAll(" +", " ");
        
        Page<Matching> data = matchingService.searchMatching(pageable, boardtype, text, date, option);
        
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("matchingList", data);

        return "search/searchMatchingList";
    }
    
    @GetMapping("/yu/search/searchLectureEvaluationList")
    public String searchLectureEvaluationListView(@RequestParam String text, 
    		@RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {
        
        text = text.trim().replaceAll(" +", " ");
        
        Page<LectureEvaluation> data = lectureEvaluationService.searchLectureEvaluation(pageable, text, date, option);
        
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("lectureEvaluationList", data);

        return "search/searchLectureEvaluationList";
    }
}
