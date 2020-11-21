package com.wp.service.searchword;

import java.util.List;

import com.wp.domain.searchword.dto.SearchWordGetDTO;
import com.wp.domain.searchword.dto.SearchWordInsertDTO;

public interface SearchWordService {
	public boolean insertSearchWord(SearchWordInsertDTO data);
	public List<SearchWordGetDTO> getWordRanking(int limit);
}
