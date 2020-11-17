package com.wp.service.matching;

import com.wp.domain.matching.dto.MatchingInsertDTO;
import com.wp.domain.matching.dto.MatchingUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.dto.MatchingGetDTO;

public interface MatchingService {
	MatchingGetDTO findById(long id);
	
	Page<Matching> findMatchingList(Pageable pageable, String boardtype);

    String InsertMatching(MatchingInsertDTO data);

    boolean ProceedMatching(long bno, String sid,String account);

    String ProceedPage(String sid, long bno);

    boolean CancelMatching(long bno);

    boolean SuccessMatching(long bno);

    boolean DeleteMatching(long bno);

    long UpdateMatching(long bno, MatchingUpdateDTO dto);

    long MaxBno();
    
    Page<Matching> searchMatching(Pageable pageable, String boardtype, String text, String date, String option);
}
