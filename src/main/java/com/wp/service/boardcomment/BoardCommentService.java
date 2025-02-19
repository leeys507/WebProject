package com.wp.service.boardcomment;

import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.boardcomment.dto.BoardCommentGetDTO;
import com.wp.domain.boardcomment.dto.BoardCommentInsertDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCommentService {
    BoardCommentGetDTO findByCno(long cno);
    Page<BoardComment> findAllBoardCommentByBno(Pageable pageable, long bno);
    public boolean insertBoardComment(BoardCommentInsertDTO data);
    public int nextGroupID();
    public boolean updateBoardComment(long bno, long cno, String content);
    public boolean deleteBoardComment(long cno);
}