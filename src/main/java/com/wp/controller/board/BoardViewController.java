package com.wp.controller.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.service.board.BoardService;
import com.wp.service.boardcomment.BoardCommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BoardViewController {
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;

    @GetMapping("/board/boardListTest")    // view
    public String openBoardListView(@RequestParam String boardtype, Model model, Pageable pageable) {
        Page<Board> page = boardService.findBoards(pageable, boardtype);
        model.addAttribute("boardType",boardtype);
        model.addAttribute("board", page);
        return "board/boardListTest";
    }
    
    @GetMapping("/board/boardInsertTest")
    public String openBoardInsertView(Model model) {
        return "board/boardInsertTest";
    }
    
    @GetMapping("/board/boardViewTest/{bno}")	// board 1 + comment
    public String openBoardView(@PathVariable long bno, Model model, Pageable pageable) {
        boardService.updateViewCnt(bno);
        BoardGetDTO dto = boardService.findById(bno);
        model.addAttribute("board", dto);
        
        Page<BoardComment> page = boardCommentService.findAllBoardCommentByBno(pageable, bno);
        model.addAttribute("boardCommentList", page);
        return "board/boardViewTest";
    }
    
    @GetMapping("/board/boardUpdate/{bno}")
    public String openBoardUpdate(@PathVariable long bno, Model model) {
        BoardGetDTO dto = boardService.findById(bno);
        model.addAttribute("board", dto);
        return "board/boardUpdateTest";
    }
}