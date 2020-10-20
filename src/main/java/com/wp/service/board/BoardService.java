package com.wp.service.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.board.dto.BoardInsertDTO;
import com.wp.domain.board.dto.BoardListGetDTO;
import com.wp.domain.board.dto.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    BoardGetDTO findById(long id);
    String InsertBoard(BoardInsertDTO data);
    List<BoardListGetDTO> findAllDesc();

    long update(long Bno, BoardUpdateDTO data);
    void updateViewCnt(long Bno);
    Page<Board> findBoards(Pageable pageable, String boardtype);

}