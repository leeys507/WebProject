package com.wp.controller.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.service.board.BoardService;
import com.wp.service.boardcomment.BoardCommentService;

import com.wp.service.student.StudentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class BoardViewController {
    private final BoardService boardService;
    private final StudentInfoService studentInfoService;
    private final BoardCommentService boardCommentService;
    private final HttpSession httpSession;
    @GetMapping("/board/boardList")    // view
    public String openBoardListView(@RequestParam String boardtype, Model model, Pageable pageable) {
        Page<Board> page = boardService.findBoards(pageable, boardtype);
        model.addAttribute("boardType", boardtype);
        model.addAttribute("board", page);
        model.addAttribute("userSid", httpSession.getAttribute("sid"));
        return "board/boardList";
    }

    @GetMapping("/board/boardInsert")
    public String openBoardInsertView(Model model) {
        String sid= (String) httpSession.getAttribute("sid");
        model.addAttribute("userSid",sid);
        model.addAttribute("userNickname",studentInfoService.getNickname(sid));
        return "board/boardInsert";
    }

    @GetMapping("/board/boardView/{bno}")	// board 1 + comment
    public String openBoardView(@PathVariable long bno, Model model, Pageable pageable) {
        boardService.updateViewCnt(bno);
        BoardGetDTO dto = boardService.findById(bno);
        String sid= (String) httpSession.getAttribute("sid");
        model.addAttribute("board", dto);
        model.addAttribute("userSid", sid);
        model.addAttribute("userNickname",studentInfoService.getNickname(sid));
        Page<BoardComment> page = boardCommentService.findAllBoardCommentByBno(pageable, bno);
        model.addAttribute("boardCommentList", page);
        return "board/boardView";
    }

    @GetMapping("/board/boardUpdate/{bno}")
    public String openBoardUpdate(@PathVariable long bno, Model model) {
        BoardGetDTO dto = boardService.findById(bno);
        model.addAttribute("board", dto);
        return "board/boardUpdate";
    }
}