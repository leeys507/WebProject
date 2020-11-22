package com.wp.domain.searchtotal;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.wp.domain.searchtotal.dto.SearchTotalGetDTO;

public interface SearchTotalCustomRepository {
    public Page<SearchTotalGetDTO> searchTotalAll(String text, Pageable pageable);
    public Page<SearchTotalGetDTO> searchTotalDates(String text, int date, Pageable pageable);
    public Page<SearchTotalGetDTO> searchTotalOptions(String addQuery, String addQuery2, String text, Pageable pageable);
    public Page<SearchTotalGetDTO> searchTotalOptionsAndDate(String addQuery, String addQuery2, String text, int date, Pageable pageable);
    
    public Page<SearchTotalGetDTO> createPage(@SuppressWarnings("rawtypes") List list, Pageable pageable, Query countQuery);
}

@Repository
@Transactional
class SearchTotalCustomRepositoryImpl implements SearchTotalCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	Long totalCount = (long) 0;
	
	public Page<SearchTotalGetDTO> searchTotalAll(String text, Pageable pageable) {
		String sql = "select title, nickname, bno, register_date, '게시판' as 'type', boardtype from board " +
				"where match(title, content) against(?1 in boolean mode) and check_delete = 'F' " +
				"union " +
				"select title, request_nickname as nickname, bno, register_date, '매칭' as 'type', boardtype from matching " +
				"where match(title, content) against(?2 in boolean mode) and check_delete = 'F'";
		
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?3";
	    }
	    else {
	    	sql += " limit ?3, ?4";
	    }
	    
	    query = entityManager.createNativeQuery(sql, "SearchTotalGetDTOMapping");
	    query.setParameter(1, text);
	    query.setParameter(2, text);
	    
	    if(pageNumber == 0) {
	    	query.setParameter(3, pageable.getPageSize());
	    }
	    else {
	    	query.setParameter(3, pageable.getOffset());
	    	query.setParameter(4, pageable.getPageSize());
	    }
	    
	    @SuppressWarnings("unchecked")
		List<SearchTotalGetDTO> list = query.getResultList();
	    
	    String countSql = "select sum(c) from " +
	    		"(select count(*) as c from board where match(title, content) against(?1 in boolean mode) and check_delete = 'F' " +
	    		"union " +
	    		"select count(*) as c from matching where match(title, content) against(?2 in boolean mode) and check_delete = 'F') as t";
	    
	    countQuery = entityManager.createNativeQuery(countSql);
    	countQuery.setParameter(1, text);
    	countQuery.setParameter(2, text);
	    
    	return createPage(list, pageable, countQuery);
	}
	
	public Page<SearchTotalGetDTO> searchTotalDates(String text, int date, Pageable pageable) {
		String sql = "select title, nickname, bno, register_date, '게시판' as 'type', boardtype from board " +
				"where match(title, content) against(?1 in boolean mode) and register_date >= DATE_SUB(NOW(), INTERVAL ?3 DAY) and check_delete = 'F' " +
				"union " +
				"select title, request_nickname as nickname, bno, register_date, '매칭' as 'type', boardtype from matching " +
				"where match(title, content) against(?2 in boolean mode) and register_date >= DATE_SUB(NOW(), INTERVAL ?4 DAY) and check_delete = 'F'";
		
		Query query = null;
		Query countQuery = null;
		int pageNumber = pageable.getPageNumber();

		if(pageNumber == 0) {
			sql += " limit ?5";
		}
		else {
			sql += " limit ?5, ?6";
		}
		
		query = entityManager.createNativeQuery(sql, "SearchTotalGetDTOMapping");
		query.setParameter(1, text);
		query.setParameter(2, text);
		query.setParameter(3, date);
		query.setParameter(4, date);
		    
		if(pageNumber == 0) {
		   query.setParameter(5, pageable.getPageSize());
		}
		else {
		   query.setParameter(5, pageable.getOffset());
		   query.setParameter(6, pageable.getPageSize());
		}
		    
		@SuppressWarnings("unchecked")
		List<SearchTotalGetDTO> list = query.getResultList();
		 
		String countSql = "select sum(c) from " +
				"(select count(*) as c from board " +
		    	"where match(title, content) against(?1 in boolean mode) and register_date >= DATE_SUB(NOW(), INTERVAL ?3 DAY) and check_delete = 'F' " +
		    	"union " +
		    	"select count(*) as c from matching " +
		    	"where match(title, content) against(?2 in boolean mode) and register_date >= DATE_SUB(NOW(), INTERVAL ?4 DAY) and check_delete = 'F') as t";
		    
		countQuery = entityManager.createNativeQuery(countSql);
		countQuery.setParameter(1, text);
	    countQuery.setParameter(2, date);
	    countQuery.setParameter(3, text);
	    countQuery.setParameter(4, date);

	    return createPage(list, pageable, countQuery);
	}
	
	public Page<SearchTotalGetDTO> searchTotalOptions(String addQuery, String addQuery2, String text, Pageable pageable) {
		String sql = "select title, nickname, bno, register_date, '게시판' as 'type', boardtype from board " + 
				"where " + addQuery + " and check_delete = 'F' " +
				"union " +
				"select title, request_nickname as nickname, bno, register_date, '매칭' as 'type', boardtype from matching " +
				"where " + addQuery2 + " and check_delete = 'F'";
		
		Query query = null;
		Query countQuery = null;
		int pageNumber = pageable.getPageNumber();

		if(pageNumber == 0) {
			sql += " limit ?3";
		}
		else {
			sql += " limit ?3, ?4";
		}
		    
		query = entityManager.createNativeQuery(sql, "SearchTotalGetDTOMapping");
		query.setParameter(1, text);
		query.setParameter(2, text);
		    
		if(pageNumber == 0) {
		   query.setParameter(3, pageable.getPageSize());
		}
		else {
		   query.setParameter(3, pageable.getOffset());
		   query.setParameter(4, pageable.getPageSize());
		}
		    
		@SuppressWarnings("unchecked")
		List<SearchTotalGetDTO> list = query.getResultList();
		 
		String countSql = "select sum(c) from " +
			   	"(select count(*) as c from board " +
			    "where " + addQuery + " and check_delete = 'F' " +
			    "union " +
			    "select count(*) as c from matching " +
			    "where " + addQuery2 + " and check_delete = 'F') as t";
			    
		countQuery = entityManager.createNativeQuery(countSql);
		countQuery.setParameter(1, text);
		countQuery.setParameter(2, text);
		    
		return createPage(list, pageable, countQuery);
	}
	
	public Page<SearchTotalGetDTO> searchTotalOptionsAndDate(String addQuery, String addQuery2, String text, int date, Pageable pageable) {	
		String sql = "select title, nickname, bno, register_date, '게시판' as 'type', boardtype from board " + 
				"where " + addQuery + " and register_date >= DATE_SUB(NOW(), INTERVAL ?3 DAY) and check_delete = 'F' " +
				"union " +
				"select title, request_nickname as nickname, bno, register_date, '매칭' as 'type', boardtype from matching " +
				"where " + addQuery2 + " and register_date >= DATE_SUB(NOW(), INTERVAL ?4 DAY) and check_delete = 'F'";
		
		Query query = null;
		Query countQuery = null;
		int pageNumber = pageable.getPageNumber();

		if(pageNumber == 0) {
			sql += " limit ?5";
		}
		else {
			sql += " limit ?5, ?6";
		}
		    
		query = entityManager.createNativeQuery(sql, "SearchTotalGetDTOMapping");
		query.setParameter(1, text);
		query.setParameter(2, text);
		query.setParameter(3, date);
		query.setParameter(4, date);
		    
		if(pageNumber == 0) {
		   query.setParameter(5, pageable.getPageSize());
		}
		else {
		   query.setParameter(5, pageable.getOffset());
		   query.setParameter(6, pageable.getPageSize());
		}
		    
		@SuppressWarnings("unchecked")
		List<SearchTotalGetDTO> list = query.getResultList();
		
		String countSql = "select sum(c) from " +
				"(select count(*) as c from board " +
		    	"where " + addQuery + " and register_date >= DATE_SUB(NOW(), INTERVAL ?3 DAY) and check_delete = 'F' " +
		    	"union " +
		    	"select count(*) as c from matching " +
		    	"where " + addQuery2 + " and register_date >= DATE_SUB(NOW(), INTERVAL ?4 DAY) and check_delete = 'F') as t";
		    
		countQuery = entityManager.createNativeQuery(countSql);
		countQuery.setParameter(1, text);
	    countQuery.setParameter(2, date);
	    countQuery.setParameter(3, text);
	    countQuery.setParameter(4, date);
		    
	    return createPage(list, pageable, countQuery);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<SearchTotalGetDTO> createPage(List list, Pageable pageable, Query countQuery) {
		int pageNumber = pageable.getPageNumber();
		
	    if(pageNumber == 0 && list.size() != 0 && list.size() >= pageable.getPageSize()) {
	    	totalCount = ((BigDecimal)countQuery.getSingleResult()).longValue();
	    }
	    else if(pageNumber != 0 && list.size() == 0) {	// page over
	    	totalCount = ((BigDecimal)countQuery.getSingleResult()).longValue();
	    }
	    
	    Page<SearchTotalGetDTO> pages = new PageImpl<SearchTotalGetDTO>(list, pageable, totalCount);
	    totalCount = BigDecimal.valueOf(0).longValue();
	    return pages;
	}
}