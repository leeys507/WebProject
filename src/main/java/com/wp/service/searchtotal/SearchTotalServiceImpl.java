package com.wp.service.searchtotal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wp.domain.searchtotal.SearchTotalCustomRepository;
import com.wp.domain.searchtotal.dto.SearchTotalGetDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchTotalServiceImpl implements SearchTotalService {
	private final SearchTotalCustomRepository searchTotalCustomRepository;
	
	 public Page<SearchTotalGetDTO> searchAll(Pageable pageable, String text, String date, String option) {
	    	String addQuery = null;
	    	String addQuery2 = null;
	    	int dateNum = 0;
	    	if(date.equals("all") && option.equals("all")) {
	    		return searchTotalCustomRepository.searchTotalAll(text, PageRequest.of(pageable.getPageNumber(), 10));
	    	}
	    	else {
	    		if(!option.equals("all")) {
	        		if(option.equals("title")) {
	        			addQuery = "match(title) against(?1 in boolean mode)";
	        			addQuery2 = "match(title) against(?2 in boolean mode)";
	        		}
	        		else if(option.equals("writer")) {
	        			addQuery = "nickname like ?1";
	        			addQuery2 = "request_nickname like ?2";
	        			text = "%" + text + "%";
	        		}
	        		else if(option.equals("commentContent")) {
	        			addQuery = "bno in (select bno from boardcomment where match(content) against(?1 in boolean mode))";
	        			addQuery2 = "bno in (select bno from matchingcomment where match(content) against(?2 in boolean mode))";
	        		}
	        		
	        		if(date.equals("all"))
	        			return searchTotalCustomRepository.searchTotalOptions(addQuery, addQuery2, text, PageRequest.of(pageable.getPageNumber(), 10));
	    		}
	    		if(!date.equals("all")) {
	        		if(date.equals("1week"))
	        			dateNum = 7;
	        		else if(date.equals("1month"))
	        			dateNum = 30;
	        		else if(date.equals("6month"))
	        			dateNum = 180;
	        		
	        		if(option.equals("all"))
	        			return searchTotalCustomRepository.searchTotalDates(text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
	    		}
	    	}
	    	return searchTotalCustomRepository.searchTotalOptionsAndDate(addQuery, addQuery2, text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
	    }
}
