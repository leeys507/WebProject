package com.wp.service.searchtotal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.searchtotal.dto.SearchTotalGetDTO;

public interface SearchTotalService {
	Page<SearchTotalGetDTO> searchAll(Pageable pageable, String text, String date, String option);
}
