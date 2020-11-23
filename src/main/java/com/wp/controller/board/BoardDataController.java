package com.wp.controller.board;

import com.wp.domain.board.dto.BoardInsertDTO;
import com.wp.domain.board.dto.BoardUpdateDTO;
import com.wp.service.board.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardDataController {
    private final BoardService boardService;

    @PostMapping("/yu/boardInsert")
    public String InsertBoard(@ModelAttribute BoardInsertDTO data) throws Exception {
        return boardService.insertBoard(data);
    }

    @PutMapping("/yu/boardUpdate/{Bno}")
    public long updateBoard(@PathVariable long Bno, @ModelAttribute BoardUpdateDTO data) throws Exception{
        return boardService.update(Bno, data);
    }
    
    @PutMapping("/yu/deleteBoard")
    public boolean deleteBoard(long bno) {
        return boardService.deleteBoard(bno);
    }
}