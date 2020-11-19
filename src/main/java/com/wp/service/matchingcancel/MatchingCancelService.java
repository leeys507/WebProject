package com.wp.service.matchingcancel;

import com.wp.domain.matchingcancel.dto.MatchingCancelInsertDTO;

public interface MatchingCancelService {
    boolean registerMatchingCancel(MatchingCancelInsertDTO data);
}
