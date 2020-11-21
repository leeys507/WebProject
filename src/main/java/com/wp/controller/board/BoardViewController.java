package com.wp.controller.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.service.board.BoardService;
import com.wp.service.boardcomment.BoardCommentService;

import com.wp.service.boardlike.BoardLikeService;
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
public class BoardViewController {
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;
    private final BoardLikeService boardLikeService;
    private final HttpSession httpSession;
    
    @GetMapping("/yu/board/boardList")    // view
    public String openBoardListView(@RequestParam String boardtype, Model model, Pageable pageable) {
    	YUFunction function = new YUFunction();
    	if(!function.boardTypeCheck(boardtype)) boardtype = "자유게시판";
    	
        Page<Board> page = boardService.findBoards(pageable, boardtype);
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("board", page);
        return "board/boardList";
    }

    @GetMapping("/yu/board/boardInsert")
    public String openBoardInsertView(String boardtype, Model model) {
    	model.addAttribute("boardtype", boardtype);
        return "board/boardInsert";
    }

    @GetMapping("/yu/board/boardView/{bno}")	// board 1 + comment
    public String openBoardView(@PathVariable long bno, Model model, Pageable pageable, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        BoardGetDTO dto = boardService.findById(bno);	// 1
        boardService.updateViewCnt(bno, dto.getReadcount(), request, response, session);
        StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");

        model.addAttribute("board", dto);
        model.addAttribute("boardlike", boardLikeService.getCheckLike(data.getSid(), bno));	// 2
        
        Page<BoardComment> page = boardCommentService.findAllBoardCommentByBno(pageable, bno);	// 3
        model.addAttribute("boardCommentList", page);
        return "board/boardView";
    }

    @GetMapping("/yu/board/boardUpdate/{bno}")
    public String openBoardUpdate(@PathVariable long bno, Model model) {
        BoardGetDTO dto = boardService.findById(bno);
        if(dto==null) {
            return "errors/errorPage";
        }
        StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        model.addAttribute("board", dto);
        if(!dto.getSid().equals(data.getSid())){
            return "errors/errorPage";
        }
        return "board/boardUpdate";
    }
}