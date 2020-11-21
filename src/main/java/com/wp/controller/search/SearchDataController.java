package com.wp.controller.search;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.searchword.dto.SearchWordInsertDTO;
import com.wp.service.searchword.SearchWordService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SearchDataController {
	private final SearchWordService searchWordService;
	
	@PostMapping("/yu/searchWordInsert")
    public boolean InsertSearchWord(@ModelAttribute SearchWordInsertDTO data) throws Exception {
        return searchWordService.insertSearchWord(data);
    }
}
