package com.wp.service.board;

import com.wp.domain.board.Board;
import com.wp.domain.board.BoardRepository;
import com.wp.domain.board.dto.BoardGetDTO;
import com.wp.domain.board.dto.BoardInsertDTO;
import com.wp.domain.board.dto.BoardListGetDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    public BoardGetDTO findById(int id){
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
}