package com.wp.service.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.BoardRepository;
import com.wp.domain.board.dto.*;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.boardcomment.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    public BoardGetDTO findById(long bno){
        Board entity = boardRepository.findByBno(bno);
        return new BoardGetDTO(entity);
    }
    
    @Transactional
    public String InsertBoard(BoardInsertDTO data) {
        return boardRepository.save(data.toEntity()).getStudentForeignkey().getSid();
    }

    public List<BoardListGetDTO> findAllDesc() {
        return boardRepository.findAllDesc()
                .stream()
                .map(BoardListGetDTO::new)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public long update(long Bno, BoardUpdateDTO data) {
        Board board = boardRepository.findById(Bno).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+Bno));
        board.update(data.getTitle(),data.getContent(), data.getBoardtype());
        return Bno;
    }
    
    @Transactional
    public void updateViewCnt(long Bno, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Cookie[] cookieFromRequest = request.getCookies();
        String cookieValue = cookieFromRequest[0].getValue();
        if (session.getAttribute("cookieview") == null) {
            session.setAttribute("cookieview", cookieValue);
        } else {
            session.setAttribute("cookie exchange", session.getAttribute("cookieview"));
            if (!session.getAttribute("cookieview").equals(cookieValue)) {
                session.setAttribute("cookieview",  cookieValue);
            }
        }if (!session.getAttribute("cookieview").equals(session.getAttribute("cookie exchange"))) {
            Board board = boardRepository.findById(Bno).orElseThrow(()->new
                    IllegalArgumentException("해당 게시글이 없습니다. id="+Bno));
            board.setRead_count(board.getRead_count()+1);
        }
    }
    
    public Page<Board> findBoards(Pageable pageable, String boardtype){
        if(boardtype==null){
            boardtype = "자유게시판";
        }
        return boardRepository.findAllByBoardtype(boardtype, PageRequest.of(pageable.getPageNumber(), 10, 
        		new Sort(Sort.Direction.DESC, "bno")));
    }

    @Transactional
    public boolean deleteBoard(long bno) {
        Board entity = boardRepository.findByBno(bno);
        if(entity == null) return false;
        entity.delete();
        return true;
    }
}