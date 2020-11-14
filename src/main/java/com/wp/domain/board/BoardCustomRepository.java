package com.wp.domain.board;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface BoardCustomRepository {
	Page<Board> searchBoardOptions(String addQuery, String text, Pageable pageable);
	Page<Board> searchBoardOptionsAndDate(String addQuery, String text, int date, Pageable pageable);
	Page<Board> createPage(@SuppressWarnings("rawtypes") List list, Pageable pageable, Query countQuery);
}

@Repository
@Transactional
class BoardCustomRepositoryImpl implements BoardCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	Long totalCount = (long) 0;

	@Override
	public Page<Board> searchBoardOptions(String addQuery, String text, Pageable pageable) {
		String sql = "select * from board where " + addQuery;
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?2";
	    }
	    else {
	    	sql += " limit ?2, ?3";
	    }
	    
	    query = entityManager.createNativeQuery(sql, Board.class);
	    query.setParameter(1, text);
	    
	    if(pageNumber == 0) {
	    	query.setParameter(2, pageable.getPageSize());
	    }
	    else {
	    	query.setParameter(2, pageable.getOffset());
	    	query.setParameter(3, pageable.getPageSize());
	    }
	    
	    @SuppressWarnings("unchecked")
		List<Board> list = query.getResultList();

    	countQuery = entityManager.createNativeQuery("select count(*) from board where " + addQuery);
    	countQuery.setParameter(1, text);

	    return createPage(list, pageable, countQuery);
	}
	
	@Override
	public Page<Board> searchBoardOptionsAndDate(String addQuery, String text, int dateNum, Pageable pageable) {
		String sql = "select * from board where " + addQuery + " and register_date >= DATE_SUB(NOW(), INTERVAL ?2 DAY)";
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?3";
	    }
	    else {
	    	sql += " limit ?3, ?4";
	    }
	    
	    query = entityManager.createNativeQuery(sql, Board.class);
	    query.setParameter(1, text);
	    query.setParameter(2, dateNum);
	    
	    if(pageNumber == 0) {
	    	query.setParameter(3, pageable.getPageSize());
	    }
	    else {
	    	query.setParameter(3, pageable.getOffset());
	    	query.setParameter(4, pageable.getPageSize());
	    }

	    @SuppressWarnings("unchecked")
		List<Board> list = query.getResultList();
	    
	    countQuery = entityManager.createNativeQuery("select count(*) from board where " + 
	    		addQuery + " and register_date >= DATE_SUB(NOW(), INTERVAL ?2 DAY)");
    	countQuery.setParameter(1, text);
    	countQuery.setParameter(2, dateNum);
    	
	    return createPage(list, pageable, countQuery);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<Board> createPage(List list, Pageable pageable, Query countQuery) {
		int pageNumber = pageable.getPageNumber();
		
	    if(pageNumber == 0 && list.size() != 0 && list.size() >= pageable.getPageSize()) {
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    else if(pageNumber != 0 && list.size() == 0) {	// page over
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    
	    Page<Board> pages = new PageImpl<Board>(list, pageable, totalCount);
	    totalCount = BigInteger.valueOf(0).longValue();
	    return pages;
	}
}