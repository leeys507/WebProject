package com.wp.service.searchword;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.wp.domain.searchword.SearchWordRepository;
import com.wp.domain.searchword.dto.SearchWordGetDTO;
import com.wp.domain.searchword.dto.SearchWordInsertDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchWordServiceImpl implements SearchWordService {
	private final SearchWordRepository searchWordRepository;
	
	@Transactional
	public boolean insertSearchWord(SearchWordInsertDTO data) {
		return searchWordRepository.save(data.toEntity()) != null;
	}
	
	public List<SearchWordGetDTO> getWordRanking(int limit) {
		return searchWordRepository.getWordRanking(limit);
	}
}
