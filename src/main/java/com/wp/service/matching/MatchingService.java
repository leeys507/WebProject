package com.wp.service.matching;

import com.wp.domain.matching.dto.MatchingInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.dto.MatchingGetDTO;

public interface MatchingService {
	MatchingGetDTO findById(long id);
	Page<Matching> findMatchingList(Pageable pageable, String boardtype);

    String InsertMatching(MatchingInsertDTO data);
}
