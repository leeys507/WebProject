package com.wp.controller.board;

import com.wp.domain.board.dto.BoardInsertDTO;
import com.wp.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardDataController {
    private final BoardService boardService;
    
    @PostMapping("/boardInsert")
    public String InsertBoard(@ModelAttribute BoardInsertDTO data) throws Exception {
        return boardService.InsertBoard(data);
    }
}