package com.wp.service.matchingcancel;

import com.wp.domain.matchingcancel.MatchingCancel;
import com.wp.domain.matchingcancel.dto.MatchingCancelInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchingCancelService {
    boolean registerMatchingCancel(MatchingCancelInsertDTO data);

    boolean findByBno(long bno);

    Page<MatchingCancel> findAllMatchingCancel(Pageable pageable);

    boolean deleteMatchingCancel(long bno);
}
