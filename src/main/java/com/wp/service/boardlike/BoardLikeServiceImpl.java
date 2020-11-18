package com.wp.service.boardlike;

import com.wp.domain.board.Board;
import com.wp.domain.board.BoardRepository;
import com.wp.domain.boardlike.BoardLike;
import com.wp.domain.boardlike.BoardLikeRepository;
import com.wp.domain.boardlike.dto.BoardLikeInsertDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardLikeServiceImpl implements BoardLikeService {
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    
    public String getCheckLike(String sid, long bno) {
        return boardLikeRepository.findCheckBySidAndBno(sid, bno);
    }
    
    @Transactional
    public boolean insertBoardLike(BoardLikeInsertDTO data) {
        Board entity = boardRepository.findByBno(data.getBno());
        entity.updatelike(1);
        return boardLikeRepository.save(data.toEntity())!=null;
    }

    @Transactional
    public int updateBoardLike(String sid, long bno) {
        Board entity = boardRepository.findByBno(bno);
        BoardLike blEntity = boardLikeRepository.findBySidAndBno(sid, bno);
        String check = boardLikeRepository.findCheckBySidAndBno(sid, bno);
        if(check.equals("F")){
            entity.updatelike(1);
            blEntity.update("T");
            return entity.getLike_count();
        }
        entity.updatelike(-1);
        blEntity.update("F");
        return entity.getLike_count();
    }
}
