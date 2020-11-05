package com.wp.service.boardlike;

import com.wp.domain.boardlike.dto.BoardLikeInsertDTO;

public interface BoardLikeService {
    String getCheckLike(String sid, long bno);

    boolean insertBoardLike(BoardLikeInsertDTO data);
    int updateBoardLike(String sid, long bno);
}
