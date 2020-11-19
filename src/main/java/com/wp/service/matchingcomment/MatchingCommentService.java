package com.wp.service.matchingcomment;

import com.wp.domain.matchingcomment.MatchingComment;
import com.wp.domain.matchingcomment.dto.MatchingCommentInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchingCommentService {
    Page<MatchingComment> findAllMatchingCommentByBno(Pageable pageable, long bno);

    boolean registerMatchingComment(MatchingCommentInsertDTO data);

    boolean updateMatchingComment(long bno, long cno, String content);

    boolean deleteMatchingComment(long cno);
}
