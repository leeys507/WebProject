package com.wp.domain.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

public interface BoardCustomRepository {
	Page<Board> searchBoardOptions(String addQuery, String text, Pageable pageable);
	Page<Board> searchBoardOptionsAndDate(String addQuery, String text, int date, Pageable pageable);
}

@Repository
@Transactional
class BoardCustomRepositoryImpl implements BoardCustomRepository {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Page<Board> searchBoardOptions(String addQuery, String text, Pageable pageable) {
		String sql = "select * from board where " + addQuery;
	    javax.persistence.Query query = entityManager.createNativeQuery(sql, Board.class);

	    query.setParameter(1, text);
	    @SuppressWarnings("unchecked")
		List<Board> list = query.getResultList();
	    
	    int start = (int)pageable.getOffset();
	    int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
	    Page<Board> pages = new PageImpl<Board>(list.subList(start, end), pageable, list.size());
	    return pages;
	}
	
	@Override
	public Page<Board> searchBoardOptionsAndDate(String addQuery, String text, int dateNum, Pageable pageable) {
		String sql = "select * from board where " + addQuery + " and register_date >= DATE_SUB(NOW(), INTERVAL ?2 DAY)";
	    javax.persistence.Query query = entityManager.createNativeQuery(sql, Board.class);
	    
	    query.setParameter(1, text);
	    query.setParameter(2, dateNum);
	    
	    @SuppressWarnings("unchecked")
		List<Board> list = query.getResultList();
	    
	    int start = (int)pageable.getOffset();
	    int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
	    Page<Board> pages = new PageImpl<Board>(list.subList(start, end), pageable, list.size());
	    return pages;
	}
}