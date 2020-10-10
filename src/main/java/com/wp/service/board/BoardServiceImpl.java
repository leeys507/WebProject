package com.wp.service.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.BoardRepository;
import com.wp.domain.board.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    public BoardGetDTO findById(long id){
        Board entity = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new BoardGetDTO(entity);
    }
    @Transactional
    public String InsertBoard(BoardInsertDTO data) {
        return boardRepository.save(data.toEntity()).getForeignkey().getSid();
    }

    public List<BoardListGetDTO> findAllDesc() {
        return boardRepository.findAllDesc()
                .stream()
                .map(BoardListGetDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public long update(long Bno, BoardUpdateDTO data) {
        Board board=boardRepository.findById(Bno).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+Bno));
        board.update(data.getTitle(),data.getContent(), data.getBoardtype());
        return Bno;
    }
    @Transactional
    public void updateViewCnt(long Bno){
        Board board = boardRepository.findById(Bno).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+Bno));
        board.setRead_count(board.getRead_count()+1);
    }
    public Page<Board> findBoards(Pageable pageable, String boardtype){
        if(boardtype==null){
            boardtype="게시판1";
        }
        return boardRepository.findAllByBoardtype(boardtype, PageRequest.of(pageable.getPageNumber(), 10));
    }

}