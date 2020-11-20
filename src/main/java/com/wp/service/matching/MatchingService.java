package com.wp.service.matching;

import com.wp.domain.matching.dto.MatchingInsertDTO;
import com.wp.domain.matching.dto.MatchingUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.dto.MatchingGetDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface MatchingService {
	MatchingGetDTO findById(long id);
	
	Page<Matching> findMatchingList(Pageable pageable, String boardtype);

    String InsertMatching(MatchingInsertDTO data);

    boolean ProceedMatching(long bno, long sid);

    String ProceedPage(String sid, long bno);

    String updateBoardOpen(String boardSid, String studentSid);

    boolean CancelMatching(long bno);

    boolean SuccessMatching(long bno);

    boolean DeleteMatching(long bno);

    long UpdateMatching(long bno, MatchingUpdateDTO dto);
    
	Page<Matching> searchMatching(Pageable pageable, String boardtype, String text, String date, String option);
	Page<Matching> searchAll(Pageable pageable, String text, String date, String option);

    long MaxBno();

    void updateViewCnt(long bno, int readcount, HttpServletRequest request, HttpServletResponse response, HttpSession session);

    boolean UpdateAccount(long bno, String account);
}
