package com.wp.domain.matching;

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


public interface MatchingCustomRepository {
	Page<Matching> searchMatchingOptions(String addQuery, String text, Pageable pageable);
	Page<Matching> searchMatchingOptionsAndDate(String addQuery, String text, int date, Pageable pageable);
	Page<Matching> createPage(@SuppressWarnings("rawtypes") List list, Pageable pageable, Query countQuery);
}

@Repository
@Transactional
class MatchingCustomRepositoryImpl implements MatchingCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	Long totalCount = (long) 0;

	@Override
	public Page<Matching> searchMatchingOptions(String addQuery, String text, Pageable pageable) {
		String sql = "select * from matching where " + addQuery + " and check_delete = 'F'";
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?2";
	    }
	    else {
	    	sql += " limit ?2, ?3";
	    }
	    
	    query = entityManager.createNativeQuery(sql, Matching.class);
	    query.setParameter(1, text);
	    
	    if(pageNumber == 0) {
	    	query.setParameter(2, pageable.getPageSize());
	    }
	    else {
	    	query.setParameter(2, pageable.getOffset());
	    	query.setParameter(3, pageable.getPageSize());
	    }
	    
	    @SuppressWarnings("unchecked")
		List<Matching> list = query.getResultList();

    	countQuery = entityManager.createNativeQuery("select count(*) from matching where " + addQuery + " and check_delete = 'F'");
    	countQuery.setParameter(1, text);

	    return createPage(list, pageable, countQuery);
	}
	
	@Override
	public Page<Matching> searchMatchingOptionsAndDate(String addQuery, String text, int dateNum, Pageable pageable) {
		String sql = "select * from matching where " + addQuery + " and register_date >= DATE_SUB(NOW(), INTERVAL ?2 DAY) and check_delete = 'F'";
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?3";
	    }
	    else {
	    	sql += " limit ?3, ?4";
	    }
	    
	    query = entityManager.createNativeQuery(sql, Matching.class);
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
		List<Matching> list = query.getResultList();
	    
	    countQuery = entityManager.createNativeQuery("select count(*) from matching where " + 
	    		addQuery + " and register_date >= DATE_SUB(NOW(), INTERVAL ?2 DAY) and check_delete = 'F'");
    	countQuery.setParameter(1, text);
    	countQuery.setParameter(2, dateNum);
    	
	    return createPage(list, pageable, countQuery);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<Matching> createPage(List list, Pageable pageable, Query countQuery) {
		int pageNumber = pageable.getPageNumber();
		
	    if(pageNumber == 0 && list.size() != 0 && list.size() >= pageable.getPageSize()) {
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    else if(pageNumber != 0 && list.size() == 0) {	// page over
	    	totalCount = ((BigInteger)countQuery.getSingleResult()).longValue();
	    }
	    
	    Page<Matching> pages = new PageImpl<Matching>(list, pageable, totalCount);
	    totalCount = BigInteger.valueOf(0).longValue();
	    return pages;
	}
}
