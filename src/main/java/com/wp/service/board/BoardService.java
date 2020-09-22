package com.wp.service.board;

import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.board.dto.BoardInsertDTO;
import com.wp.domain.board.dto.BoardListGetDTO;

import java.util.List;

public interface BoardService {
    BoardGetDTO findById(int id);
    String InsertBoard(BoardInsertDTO data);
    List<BoardListGetDTO> findAllDesc();
}