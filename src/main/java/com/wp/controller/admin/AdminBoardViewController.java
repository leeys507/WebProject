package com.wp.controller.admin;

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
public class AdminBoardViewController {
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;

    @GetMapping("/yu/admin/boardList")    // view
    public String openAdminBoardListView(@RequestParam String boardtype, Model model, Pageable pageable) {
        YUFunction function = new YUFunction();
        if(!function.boardTypeCheck(boardtype)) boardtype = "자유게시판";

        Page<Board> page = boardService.findBoards(pageable, boardtype);
        model.addAttribute("boardtype", boardtype);
        model.addAttribute("board", page);
        return "admin/adminBoardList";
    }

    @GetMapping("/yu/admin/boardView/{bno}")	// board 1 + comment
    public String openAdminBoardView(@PathVariable long bno, Model model, Pageable pageable, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        BoardGetDTO dto = boardService.findById(bno);	// 1

        model.addAttribute("board", dto);

        Page<BoardComment> page = boardCommentService.findAllBoardCommentByBno(pageable, bno);	// 3
        model.addAttribute("boardCommentList", page);
        return "admin/adminBoardView";
    }
}
