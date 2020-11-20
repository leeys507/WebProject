package com.wp.service.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.board.dto.BoardInsertDTO;
import com.wp.domain.board.dto.BoardListGetDTO;
import com.wp.domain.board.dto.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {
    BoardGetDTO findById(long id);
    String InsertBoard(BoardInsertDTO data);
    List<BoardListGetDTO> findAllDesc();

    long update(long Bno, BoardUpdateDTO data);
    void updateViewCnt(long Bno, int present_read_count, HttpServletRequest request, HttpServletResponse response, HttpSession session);
    
    Page<Board> findBoards(Pageable pageable, String boardtype);
    
    Page<Board> searchAll(Pageable pageable, String text, String date, String option);
    Page<Board> searchBoard(Pageable pageable, String boardtype, String text, String date, String option);

    boolean deleteBoard(long bno);

    String updateBoardOpen(String boardSid, String studentSid);
}