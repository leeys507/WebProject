package com.wp.controller.matching;

import javax.servlet.http.HttpSession;

import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.matching.ChatRoomRepository;
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
import com.wp.service.student.StudentInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MatchingViewController {
	private final StudentInfoService studentInfoService;
	private final MatchingService matchingService;
	private final HttpSession httpSession;
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/matching/matchingList")    // view
    public String openMatchingListView(@RequestParam String boardtype, Model model, Pageable pageable) {
        Page<Matching> page = matchingService.findMatchingList(pageable, boardtype);
        model.addAttribute("boardType", boardtype);
        model.addAttribute("matchingList", page);
        model.addAttribute("userSid",httpSession.getAttribute("sid"));
        return "matching/matchingList";
    }
    @GetMapping("/matching/matchingInsert")
    public String openMatchingInsertView(Model model) {
        String sid= (String) httpSession.getAttribute("sid");
        model.addAttribute("userSid",sid);
        model.addAttribute("userphonenum",studentInfoService.getPhoneNum(sid));
        model.addAttribute("userNickname",studentInfoService.getNickname(sid));
        return "matching/matchingInsert";
    }
    @GetMapping("/matching/matchingView/{bno}")
    public String openMatchingView(@PathVariable long bno, Model model) {
        MatchingGetDTO dto = matchingService.findById(bno);
        System.out.println(dto == null ? "true" : "false");
        String sid = (String) httpSession.getAttribute("sid");
        model.addAttribute("matching", dto);
        model.addAttribute("userSid", sid);
        model.addAttribute("userNickname", studentInfoService.getNickname(sid));
        return "matching/matchingView";
    }
    @GetMapping("/matching/matchingProceeding/{bno}")
    public String openMatcingProceeding(@PathVariable long bno,Model model){
        String sid = (String) httpSession.getAttribute("sid");
        model.addAttribute("userSid", sid);
        model.addAttribute("bno", bno);
        model.addAttribute("requestSid",matchingService.findById(bno).getRequest_sid());
        model.addAttribute("chatRoom",chatRoomRepository.findRoomById(String.valueOf(bno)));
        return matchingService.ProceedPage(sid,bno);
    }
    @GetMapping("/matching/matchingUpdate/{bno}")
    public String openBoardUpdate(@PathVariable long bno, Model model) {
        MatchingGetDTO dto = matchingService.findById(bno);
        model.addAttribute("matching", dto);
        return "matching/matchingUpdate";
    }

}
