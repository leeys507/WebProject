package com.wp.service.matching;

import com.wp.domain.matching.dto.MatchingInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.MatchingRepository;
import com.wp.domain.matching.dto.MatchingGetDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MatchingServiceImpl implements MatchingService {
	private final MatchingRepository matchingRepository;
	
    public MatchingGetDTO findById(long id){
        Matching entity = matchingRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new MatchingGetDTO(entity);
    }
    
    public Page<Matching> findMatchingList(Pageable pageable, String boardtype){
        if(boardtype==null){
            boardtype = "심부름";
        }
        return matchingRepository.findAllByBoardtype(boardtype, PageRequest.of(pageable.getPageNumber(), 10, 
        		new Sort(Sort.Direction.DESC, "bno")));
    }
    @Transactional
    public String InsertMatching(MatchingInsertDTO data) {
        return matchingRepository.save(data.toEntity()).getStudentForeignkey_request().getSid();
    }
}
