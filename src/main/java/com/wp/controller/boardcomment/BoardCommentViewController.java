package com.wp.controller.boardcomment;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.wp.domain.boardcomment.BoardComment;
//import com.wp.service.boardcomment.BoardCommentService;
//
//import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
//@Controller
//public class BoardCommentViewController {
//    private final BoardCommentService boardCommentService;
//
//    @GetMapping("/boardComment/boardCommentTest/{bno}")    // view
//    public String openBoardCommentListView(@PathVariable long bno, Model model, Pageable pageable) {
//        Page<BoardComment> page = boardCommentService.findAllBoardCommentsByBno(pageable, bno);
//        model.addAttribute("boardCommentList", page);
//        return "board/boardListTest";
//    }
//}
