package com.wp.controller.board;

import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RequiredArgsConstructor
@Controller
public class BoardViewController {
    private final BoardService boardService;

    @GetMapping("/board/boardListTest")
    public String openBoardListView(Model model) {
        model.addAttribute("board",boardService.findAllDesc());
        return "board/boardListTest";
    }
    @GetMapping("/board/boardInsertTest")
    public String openBoardInsertView(Model model) {

        return "board/boardInsertTest";
    }
    @GetMapping("/board/boardViewTest/{bno}")
    public String openBoardView(@PathVariable int bno, Model model) {
        BoardGetDTO dto= boardService.findById(bno);
        model.addAttribute("board",dto);
        return "board/boardViewTest";
    }
}