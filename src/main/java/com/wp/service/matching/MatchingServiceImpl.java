package com.wp.service.matching;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.MatchingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MatchingServiceImpl implements MatchingService {
	private final MatchingRepository matchingRepository;
	
    public Page<Matching> findMatchingList(Pageable pageable, String boardtype){
        if(boardtype==null){
            boardtype = "심부름";
        }
        return matchingRepository.findAllByBoardtype(boardtype, PageRequest.of(pageable.getPageNumber(), 10, 
        		new Sort(Sort.Direction.DESC, "bno")));
    }
}
