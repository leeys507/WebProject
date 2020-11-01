package com.wp.service.matching;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.matching.Matching;

public interface MatchingService {
	Page<Matching> findMatchingList(Pageable pageable, String boardtype);
}
