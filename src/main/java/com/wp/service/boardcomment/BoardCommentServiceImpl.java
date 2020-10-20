package com.wp.service.boardcomment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wp.domain.boardcomment.BoardCommentRepository;
import com.wp.domain.boardcomment.dto.BoardCommentGetDTO;
import com.wp.domain.boardcomment.dto.BoardCommentInsertDTO;

import lombok.RequiredArgsConstructor;

import com.wp.domain.board.Board;
import com.wp.domain.board.BoardRepository;
import com.wp.domain.boardcomment.BoardComment;

@RequiredArgsConstructor
@Service
public class BoardCommentServiceImpl implements BoardCommentService {
	private final BoardCommentRepository boardCommentRepository;
	private final BoardRepository boardRepository;
	
	public BoardCommentGetDTO findByCno(long cno) {
		BoardComment entity = boardCommentRepository.findByCno(cno);
		return (entity == null) ? null : new BoardCommentGetDTO(entity);
	}
    
    public Page<BoardComment> findAllBoardCommentByBno(Pageable pageable, long bno){
    	Board data = boardRepository.findById(bno).get();
        return boardCommentRepository.findAllByBoardForeignkey(data, PageRequest.of(pageable.getPageNumber(), 10));
    }
    
    @Transactional
    public boolean registerBoardComment(BoardCommentInsertDTO data) {
    	return boardCommentRepository.save(data.toEntity()) != null;
    }
    
    public int nextGroupID() {
    	return boardCommentRepository.getBoardCommentCount() == 0 ? 1 : boardCommentRepository.findMaxGroupID() + 1;
    }
}
