package com.wp.controller.admin;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.dto.MatchingGetDTO;
import com.wp.domain.matchingcancel.MatchingCancel;
import com.wp.domain.matchingcomment.MatchingComment;
import com.wp.service.matching.MatchingService;
import com.wp.service.matchingcancel.MatchingCancelService;
import com.wp.service.matchingcomment.MatchingCommentService;
import com.wp.yufunction.YUFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class AdminMatchingViewController {
    private final MatchingCommentService matchingCommentService;
    private final MatchingService matchingService;
    private final MatchingCancelService matchingCancelService;
    @GetMapping("/yu/admin/matchingList")    // view
    public String openMatchingListView(@RequestParam String boardtype, Model model, Pageable pageable) {
        YUFunction function = new YUFunction();
        if(!function.matchingTypeCheck(boardtype)) boardtype = "청소";

        Page<Matching> page = matchingService.findMatchingList(pageable, boardtype);

        model.addAttribute("boardtype", boardtype);
        model.addAttribute("matchingList", page);
        return "admin/adminMatchingList";
    }
    @GetMapping("/yu/admin/matchingView/{bno}")
    public String openMatchingView(@PathVariable long bno, Model model, Pageable pageable, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        MatchingGetDTO dto = matchingService.findById(bno);
        matchingService.updateViewCnt(bno, dto.getReadcount(), request, response, session);
        model.addAttribute("matching", dto);
        Page<MatchingComment> page = matchingCommentService.findAllMatchingCommentByBno(pageable, bno);	// 3
        model.addAttribute("matchingCommentList", page);
        return "admin/adminMatchingView";
    }
    @GetMapping("/yu/admin/matchingCancelList")
    public String openMatchingCancelView(Model model, Pageable pageable) {
        Page<MatchingCancel> page = matchingCancelService.findAllMatchingCancel(pageable);	// 3
        model.addAttribute("matchingCancelList", page);
        return "admin/adminMatchingCancelView";
    }
    @GetMapping("/yu/admin/matchingDeleteList")
    public String openMatchingDeleteList(Model model, Pageable pageable) {
        Page<Matching> page = matchingService.findAllDeleteMatching(pageable);	// 3
        model.addAttribute("matchingList", page);
        return "admin/adminMatchingDeleteListView";
    }
}