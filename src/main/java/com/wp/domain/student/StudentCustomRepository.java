package com.wp.domain.student;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.wp.domain.student.dto.StudentGetMyBoardDTO;
import com.wp.domain.student.dto.StudentGetMyCommentDTO;

public interface StudentCustomRepository {
	List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit);
	public Page<StudentGetMyBoardDTO> getMyAllBoard(String sid, Pageable pageable);
	List<StudentGetMyCommentDTO> getMyComment(String sid, int limit);
	public Page<StudentGetMyCommentDTO> getMyAllComment(String sid, Pageable pageable);
	Page<StudentGetMyBoardDTO> createMyBoardPage(@SuppressWarnings("rawtypes") List list, Pageable pageable, Query countQuery);
	Page<StudentGetMyCommentDTO> createMyCommentPage(@SuppressWarnings("rawtypes") List list, Pageable pageable, Query countQuery);
}

@Repository
@Transactional
class StudentCustomRepositoryImpl implements StudentCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	Long totalCount = (long) 0;
	
	public List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit) {
		String sql = "select title, bno, register_date, check_delete, '게시판' as 'type', boardtype from board where sid = ?1 " +
				"union " +
				"select title, bno, register_date, check_delete, '매칭게시판' as 'type', '매칭게시판' as boardtype from matching where request_sid = ?2 " +
				"order by register_date desc limit ?3";
		
	    Query query = null;

	    query = entityManager.createNativeQuery(sql, "StudentGetMyBoardDTOMapping");
	    query.setParameter(1, sid);
	    query.setParameter(2, sid);
	    query.setParameter(3, limit);
	    
	    @SuppressWarnings("unchecked")
		List<StudentGetMyBoardDTO> list = query.getResultList();
	    
	    return list;
	}
	
	public Page<StudentGetMyBoardDTO> getMyAllBoard(String sid, Pageable pageable) {
		String sql = "select title, bno, register_date, check_delete, '게시판' as 'type', boardtype from board where sid = ?1 " +
				"union " +
				"select title, bno, register_date, check_delete, '매칭게시판' as 'type', '매칭게시판' as boardtype from matching where request_sid = ?2 " +
				"order by register_date desc";
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?3";
	    }
	    else {
	    	sql += " limit ?3, ?4";
	    }

	    query = entityManager.createNativeQuery(sql, "StudentGetMyBoardDTOMapping");
	    query.setParameter(1, sid);
	    query.setParameter(2, sid);
	    
	    if(pageNumber == 0) {
	    	query.setParameter(3, pageable.getPageSize());
	    }
	    else {
	    	query.setParameter(3, pageable.getOffset());
	    	query.setParameter(4, pageable.getPageSize());
	    }
	    
	    @SuppressWarnings("unchecked")
		List<StudentGetMyBoardDTO> list = query.getResultList();
	    
	    String countSql = "select sum(c) from " +
	    		"(select count(*) as c from board where sid = ?1 " +
	    		"union " +
	    		"select count(*) as c from matching where request_sid = ?2) as t";
	    
	    countQuery = entityManager.createNativeQuery(countSql);
    	countQuery.setParameter(1, sid);
    	countQuery.setParameter(2, sid);
	    
    	return createMyBoardPage(list, pageable, countQuery);
	}
	
	public List<StudentGetMyCommentDTO> getMyComment(String sid, int limit) {
		String sql = "select bc.content, b.bno, bc.register_date, bc.check_delete, b.boardtype from board b, boardcomment bc " +
				"where b.bno = bc.bno and bc.sid = ?1 order by bc.register_date desc limit ?2";
		
	    Query query = null;

	    query = entityManager.createNativeQuery(sql, "StudentGetMyCommentDTOMapping");
	    query.setParameter(1, sid);
	    query.setParameter(2, limit);
	    
	    @SuppressWarnings("unchecked")
		List<StudentGetMyCommentDTO> list = query.getResultList();
	    
	    return list;
	}
	
	public Page<StudentGetMyCommentDTO> getMyAllComment(String sid, Pageable pageable) {
		String sql = "select bc.content, b.bno, bc.register_date, bc.check_delete, b.boardtype from board b, boardcomment bc " +
				"where b.bno = bc.bno and bc.sid = ?1 order by bc.register_date desc";
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?2";
	    }
	    else {
	    	sql += " limit ?2, ?3";
	    }

	    query = entityManager.createNativeQuery(sql, "StudentGetMyCommentDTOMapping");
	    query.setParameter(1, sid);
	    
	    if(pageNumber == 0) {
	    	query.setParameter(2, pageable.getPageSize());
	    }
	    else {
	    	query.setParameter(2, pageable.getOffset());
	    	query.setParameter(3, pageable.getPageSize());
	    }
	    
	    @SuppressWarnings("unchecked")
		List<StudentGetMyCommentDTO> list = query.getResultList();
	    
	    String countSql = "select count(*) from board b, boardcomment bc" +
	    		" where b.bno = bc.bno and bc.sid = ?1";
	    
	    countQuery = entityManager.createNativeQuery(countSql);
    	countQuery.setParameter(1, sid);
	    
    	return createMyCommentPage(list, pageable, countQuery);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<StudentGetMyBoardDTO> createMyBoardPage(List list, Pageable pageable, Query countQuery) {
		int pageNumber = pageable.getPageNumber();
		
	    if(pageNumber == 0 && list.size() != 0 && list.size() >= pageable.getPageSize()) {
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    else if(pageNumber != 0 && list.size() == 0) {	// page over
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    
	    Page<StudentGetMyBoardDTO> pages = new PageImpl<StudentGetMyBoardDTO>(list, pageable, totalCount);
	    totalCount = BigInteger.valueOf(0).longValue();
	    return pages;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<StudentGetMyCommentDTO> createMyCommentPage(List list, Pageable pageable, Query countQuery) {
		int pageNumber = pageable.getPageNumber();
		
	    if(pageNumber == 0 && list.size() != 0 && list.size() >= pageable.getPageSize()) {
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    else if(pageNumber != 0 && list.size() == 0) {	// page over
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    
	    Page<StudentGetMyCommentDTO> pages = new PageImpl<StudentGetMyCommentDTO>(list, pageable, totalCount);
	    totalCount = BigInteger.valueOf(0).longValue();
	    return pages;
	}
}