package com.wp.domain.student;

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

import com.wp.domain.student.dto.StudentGetMyBoardDTO;
import com.wp.domain.student.dto.StudentGetMyCommentDTO;

public interface StudentCustomRepository {
	List<String> getMySearchWord(String sid, int intervalDay, int limit);
	int getMyCommentCount(String sid);
	List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit);
	Page<StudentGetMyBoardDTO> getMyAllBoard(String sid, Pageable pageable);
	List<StudentGetMyCommentDTO> getMyComment(String sid, int limit);
	Page<StudentGetMyCommentDTO> getMyAllComment(String sid, Pageable pageable);
	Page<StudentGetMyBoardDTO> createMyBoardPage(@SuppressWarnings("rawtypes") List list, Pageable pageable, Query countQuery);
	Page<StudentGetMyCommentDTO> createMyCommentPage(@SuppressWarnings("rawtypes") List list, Pageable pageable, Query countQuery);
}

@Repository
@Transactional
class StudentCustomRepositoryImpl implements StudentCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	Long totalCount = (long) 0;
	
	public List<String> getMySearchWord(String sid, int intervalDay, int limit) {
		String sql = "select distinct word from searchword where sid = ?1 and register_date > date_sub(now(), interval ?2 day) limit ?3";
		
	    Query query = null;

	    query = entityManager.createNativeQuery(sql);
	    query.setParameter(1, sid);
	    query.setParameter(2, intervalDay);
	    query.setParameter(3, limit);
	    
	    @SuppressWarnings("unchecked")
		List<String> list = query.getResultList();
	    
	    return list;
	}
	
	public int getMyCommentCount(String sid) {
	    String sql = "select sum(c) from "
	    		+ "(select count(*) as c from board b, boardcomment bc "
	    		+ "where b.bno = bc.bno and bc.sid = ?1 and bc.check_delete = 'F' "
	    		+ "union all "
	    		+ "select count(*) as c from matching m, matchingcomment mc "
	    		+ "where m.bno = mc.bno and mc.sid = ?2 and mc.check_delete = 'F') as t";
	    
	    
	    Query query = null;

	    query = entityManager.createNativeQuery(sql);
	    query.setParameter(1, sid);
	    query.setParameter(2, sid);
	    
		int count = ((BigDecimal)query.getSingleResult()).intValue();
	    
	    return count;
	}
	
	public List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit) {
		String sql = "select title, bno, register_date, '게시판' as 'type', boardtype from board where sid = ?1 and check_delete = 'F' "
				+ "union all "
				+ "select title, bno, register_date, '매칭' as 'type', boardtype from matching "
				+ "where request_sid = ?2 and check_delete = 'F'"
				+ "union all "
				+ "select lecturename as title, lno as bno, register_date, '강의평가' as 'type', '강의평가' as 'boardtype' from lectureevaluation "
				+ "where sid = ?3 and check_delete = 'F' "
				+ "order by register_date desc limit ?4";
		
	    Query query = null;

	    query = entityManager.createNativeQuery(sql, "StudentGetMyBoardDTOMapping");
	    query.setParameter(1, sid);
	    query.setParameter(2, sid);
	    query.setParameter(3, sid);
	    query.setParameter(4, limit);
	    
	    @SuppressWarnings("unchecked")
		List<StudentGetMyBoardDTO> list = query.getResultList();
	    
	    return list;
	}
	
	public Page<StudentGetMyBoardDTO> getMyAllBoard(String sid, Pageable pageable) {
		String sql = "select title, bno, register_date, '게시판' as 'type', boardtype from board where sid = ?1 and check_delete = 'F' "
				+ "union all "
				+ "select title, bno, register_date, '매칭' as 'type', boardtype from matching "
				+ "where request_sid = ?2 and check_delete = 'F' "
				+ "union all "
				+ "select lecturename as title, lno as bno, register_date, '강의평가' as 'type', '강의평가' as 'boardtype' from lectureevaluation "
				+ "where sid = ?3 and check_delete = 'F' "
				+ "order by register_date desc";
		
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?4";
	    }
	    else {
	    	sql += " limit ?4, ?5";
	    }

	    query = entityManager.createNativeQuery(sql, "StudentGetMyBoardDTOMapping");
	    query.setParameter(1, sid);
	    query.setParameter(2, sid);
	    query.setParameter(3, sid);
	    
	    if(pageNumber == 0) {
	    	query.setParameter(4, pageable.getPageSize());
	    }
	    else {
	    	query.setParameter(4, pageable.getOffset());
	    	query.setParameter(5, pageable.getPageSize());
	    }
	    
	    @SuppressWarnings("unchecked")
		List<StudentGetMyBoardDTO> list = query.getResultList();
	    
	    String countSql = "select sum(c) from " +
	    		"(select count(*) as c from board where sid = ?1 and check_delete = 'F' " +
	    		"union all " +
	    		"select count(*) as c from matching where request_sid = ?2 and check_delete = 'F'" +
	    		"union all " +
	    		"select count(*) as c from lectureevaluation where sid = ?3 and check_delete = 'F') as t";
	    
	    countQuery = entityManager.createNativeQuery(countSql);
    	countQuery.setParameter(1, sid);
    	countQuery.setParameter(2, sid);
    	countQuery.setParameter(3, sid);
	    
    	return createMyBoardPage(list, pageable, countQuery);
	}
	
	public List<StudentGetMyCommentDTO> getMyComment(String sid, int limit) {
		String sql = "select bc.content as content, b.bno as bno, bc.register_date as register_date, '게시판' as type, b.boardtype as boardtype from board b, boardcomment bc "
				+ "where b.bno = bc.bno and bc.sid = ?1 and bc.check_delete = 'F' "
				+ "union all "
				+ "select mc.content as content, m.bno as bno, mc.register_date as register_date, '매칭' as type, m.boardtype as boardtype from matching m, matchingcomment mc "
				+ "where m.bno = mc.bno and mc.sid = ?2 and mc.check_delete = 'F' "
				+ "order by register_date desc limit ?3";
		
	    Query query = null;

	    query = entityManager.createNativeQuery(sql, "StudentGetMyCommentDTOMapping");
	    query.setParameter(1, sid);
	    query.setParameter(2, sid);
	    query.setParameter(3, limit);
	    
	    @SuppressWarnings("unchecked")
		List<StudentGetMyCommentDTO> list = query.getResultList();
	    
	    return list;
	}
	
	public Page<StudentGetMyCommentDTO> getMyAllComment(String sid, Pageable pageable) {
		String sql = "select bc.content as content, b.bno as bno, bc.register_date as register_date, '게시판' as type, b.boardtype as boardtype from board b, boardcomment bc "
				+ "where b.bno = bc.bno and bc.sid = ?1 and bc.check_delete = 'F' "
				+ "union all "
				+ "select mc.content as content, m.bno as bno, mc.register_date as register_date, '매칭' as type, m.boardtype as boardtype from matching m, matchingcomment mc "
				+ "where m.bno = mc.bno and mc.sid = ?2 and mc.check_delete = 'F' "
				+ "order by register_date desc";
	    Query query = null;
	    Query countQuery = null;
	    int pageNumber = pageable.getPageNumber();

	    if(pageNumber == 0) {
	    	sql += " limit ?3";
	    }
	    else {
	    	sql += " limit ?3, ?4";
	    }

	    query = entityManager.createNativeQuery(sql, "StudentGetMyCommentDTOMapping");
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
		List<StudentGetMyCommentDTO> list = query.getResultList();
	    
	    String countSql = "select sum(c) from "
	    		+ "(select count(*) as c from board b, boardcomment bc "
	    		+ "where b.bno = bc.bno and bc.sid = ?1 and bc.check_delete = 'F' "
	    		+ "union all "
	    		+ "select count(*) as c from matching m, matchingcomment mc "
	    		+ "where m.bno = mc.bno and mc.sid = ?2 and mc.check_delete = 'F') as t";
	    
	    countQuery = entityManager.createNativeQuery(countSql);
    	countQuery.setParameter(1, sid);
    	countQuery.setParameter(2, sid);
	    
    	return createMyCommentPage(list, pageable, countQuery);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<StudentGetMyBoardDTO> createMyBoardPage(List list, Pageable pageable, Query countQuery) {
		int pageNumber = pageable.getPageNumber();
		
	    if(pageNumber == 0 && list.size() != 0 && list.size() >= pageable.getPageSize()) {
	    	totalCount = ((BigDecimal)countQuery.getSingleResult()).longValue();
	    }
	    else if(pageNumber != 0 && list.size() == 0) {	// page over
	    	totalCount = ((BigDecimal)countQuery.getSingleResult()).longValue();
	    }
	    
	    Page<StudentGetMyBoardDTO> pages = new PageImpl<StudentGetMyBoardDTO>(list, pageable, totalCount);
	    totalCount = BigDecimal.valueOf(0).longValue();
	    return pages;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<StudentGetMyCommentDTO> createMyCommentPage(List list, Pageable pageable, Query countQuery) {
		int pageNumber = pageable.getPageNumber();
		
	    if(pageNumber == 0 && list.size() != 0 && list.size() >= pageable.getPageSize()) {
	    	totalCount = ((BigDecimal)countQuery.getSingleResult()).longValue();
	    }
	    else if(pageNumber != 0 && list.size() == 0) {	// page over
	    	totalCount = ((BigDecimal)countQuery.getSingleResult()).longValue();
	    }
	    
	    Page<StudentGetMyCommentDTO> pages = new PageImpl<StudentGetMyCommentDTO>(list, pageable, totalCount);
	    totalCount = BigDecimal.valueOf(0).longValue();
	    return pages;
	}
}