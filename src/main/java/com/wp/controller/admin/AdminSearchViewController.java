package com.wp.controller.admin;

import com.wp.domain.board.Board;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.matching.Matching;
import com.wp.domain.searchtotal.dto.SearchTotalGetDTO;
import com.wp.service.board.BoardService;
import com.wp.service.lectureevaluation.LectureEvaluationService;
import com.wp.service.matching.MatchingService;
import com.wp.service.matchingcancel.MatchingCancelService;
import com.wp.service.searchtotal.SearchTotalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class AdminSearchViewController {
    private final BoardService boardService;
    private final MatchingCancelService matchingCancelService;
    private final MatchingService matchingService;
    private final LectureEvaluationService lectureEvaluationService;
    private final SearchTotalService searchTotalService;

    @GetMapping("/yu/admin/searchAllList")
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
            return "admin/adminSearchAllList";
        }
        else if(type.equals("board")) {
            Page<Board> boardList = null;
            boardList = boardService.searchAll(pageable, text, date, option);
            model.addAttribute("board", boardList);
            return "admin/adminSearchAllBoardList";
        }
        else if(type.equals("matching")) {
            Page<Matching> matchingList = null;
            matchingList = matchingService.searchAll(pageable, text, date, option);
            model.addAttribute("matchingList", matchingList);
            return "admin/adminAllMatchingList";
        }
        return "errors/errorPage";
    }

    @GetMapping("/yu/admin/searchBoardList")
    public String searchBoardListView(@RequestParam String boardtype, @RequestParam String text,
                                      @RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {

        text = text.trim().replaceAll(" +", " ");

        Page<Board> data = boardService.searchBoard(pageable, boardtype, text, date, option);

        model.addAttribute("boardtype", boardtype);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("board", data);

        return "admin/adminSearchBoardList";
    }

    @GetMapping("/yu/admin/searchMatchingList")
    public String searchMatchingListView(@RequestParam String boardtype, @RequestParam String text,
                                         @RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {

        text = text.trim().replaceAll(" +", " ");

        Page<Matching> data = matchingService.searchMatching(pageable, boardtype, text, date, option);
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("matchingList", data);

        return "admin/adminSearchMatchingList";
    }

    @GetMapping("/yu/admin/searchLectureEvaluationList")
    public String searchLectureEvaluationListView(@RequestParam String text,
                                                  @RequestParam String date, @RequestParam String option, Model model, Pageable pageable) {

        text = text.trim().replaceAll(" +", " ");

        Page<LectureEvaluation> data = lectureEvaluationService.searchLectureEvaluation(pageable, text, date, option);

        model.addAttribute("text", text);
        model.addAttribute("date", date);
        model.addAttribute("option", option);
        model.addAttribute("lectureEvaluationList", data);

        return "admin/adminSearchLectureEvaluationList";
    }
}
