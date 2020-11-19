package com.wp.controller.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.domain.matching.ChatRoomRepository;
import com.wp.domain.matchingcomment.MatchingComment;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.service.matchingcomment.MatchingCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.dto.MatchingGetDTO;
import com.wp.service.matching.MatchingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MatchingViewController {
    private final MatchingCommentService matchingCommentService;
	private final MatchingService matchingService;
	private final HttpSession httpSession;
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/yu/matching/matchingList")    // view
    public String openMatchingListView(@RequestParam String boardtype, Model model, Pageable pageable) {
        Page<Matching> page = matchingService.findMatchingList(pageable, boardtype);

        model.addAttribute("boardtype", boardtype);
        model.addAttribute("matchingList", page);
        model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
        return "matching/matchingList";
    }
    
    @GetMapping("/yu/matching/matchingInsert")
    public String openMatchingInsertView(@RequestParam String boardtype, Model model) {
    	model.addAttribute("boardtype", boardtype);
    	model.addAttribute("studentInfo", httpSession.getAttribute("studentInfo"));
        //model.addAttribute("bno",matchingService.MaxBno());
        return "matching/matchingInsert";
    }
    
    @GetMapping("/yu/matching/matchingView/{bno}")
    public String openMatchingView(@PathVariable long bno, Model model, Pageable pageable, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        MatchingGetDTO dto = matchingService.findById(bno);
        matchingService.updateViewCnt(bno, dto.getReadcount(), request, response, session);
        model.addAttribute("matching", dto);
        model.addAttribute("studentInfo", (StudentGetDTO) httpSession.getAttribute("studentInfo"));
        Page<MatchingComment> page = matchingCommentService.findAllMatchingCommentByBno(pageable, bno);	// 3
        model.addAttribute("matchingCommentList", page);
        return "matching/matchingView";
    }
    
    @GetMapping("/yu/matching/matchingProceeding/{bno}")
    public String openMatchingProceeding(@PathVariable long bno,Model model){
        StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        model.addAttribute("studentInfo", data);
        model.addAttribute("matching", matchingService.findById(bno));
        model.addAttribute("chatRoom", chatRoomRepository.findRoomById(String.valueOf(bno)));
        return matchingService.ProceedPage(data.getSid(),bno);
    }
    
    @GetMapping("/yu/matching/matchingUpdate/{bno}")
    public String openBoardUpdate(@PathVariable long bno, Model model) {
        MatchingGetDTO dto = matchingService.findById(bno);
        StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        model.addAttribute("matching", dto);
        return matchingService.updateBoardOpen(dto.getRequest_sid(),data.getSid());
    }
}
