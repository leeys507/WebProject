package com.wp.service.matching;

import com.wp.domain.matching.dto.MatchingInsertDTO;
import com.wp.domain.matching.dto.MatchingUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.dto.MatchingGetDTO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface MatchingService {
	MatchingGetDTO findById(long id);
	
	Page<Matching> findMatchingList(Pageable pageable, String boardtype);

    String InsertMatching(MatchingInsertDTO data);

    boolean ProceedMatching(long bno, long sid);

    String ProceedPage(String sid, long bno);

    boolean CancelMatching(long bno);

    boolean SuccessMatching(long bno);

    boolean DeleteMatching(long bno);

    long UpdateMatching(long bno, MatchingUpdateDTO dto);

    long MaxBno();
    
    List<Matching> getMatchingList();
    
    Page<Matching> searchMatching(Pageable pageable, String boardtype, String text, String date, String option);

    Page<Matching> searchAll(Pageable pageable, String text, String date, String option);

    void updateViewCnt(long bno, int readcount, HttpServletRequest request, HttpServletResponse response, HttpSession session);

    boolean UpdateAccount(long bno, String account);

    Page<Matching> findAllDeleteMatching(Pageable pageable);
}
