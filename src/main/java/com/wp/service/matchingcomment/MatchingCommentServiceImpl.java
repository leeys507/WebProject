package com.wp.service.matchingcomment;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.MatchingRepository;
import com.wp.domain.matchingcomment.MatchingComment;
import com.wp.domain.matchingcomment.MatchingCommentRepository;
import com.wp.domain.matchingcomment.dto.MatchingCommentInsertDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MatchingCommentServiceImpl implements MatchingCommentService {
    private final MatchingRepository matchingRepository;
    private final MatchingCommentRepository matchingCommentRepository;

    public Page<MatchingComment> findAllMatchingCommentByBno(Pageable pageable, long bno) {
        Matching data = matchingRepository.findById(bno).get();
        return matchingCommentRepository.findAllByBoardForeignkey(data, PageRequest.of(pageable.getPageNumber(), 10));
    }

    @Transactional
    public boolean registerMatchingComment(MatchingCommentInsertDTO data) {
        return matchingCommentRepository.save(data.toEntity())!=null;
    }

    @Transactional
    public boolean updateMatchingComment(long bno, long cno, String content) {
        MatchingComment entity = matchingCommentRepository.findByBnoAndCno(bno, cno);
        if(entity == null) return false;
        entity.update(content);
        return true;
    }

    @Transactional
    public boolean deleteMatchingComment(long cno) {
        MatchingComment entity = matchingCommentRepository.findByCno(cno);
        if(entity == null) return false;
        entity.delete();
        return true;
    }
}
