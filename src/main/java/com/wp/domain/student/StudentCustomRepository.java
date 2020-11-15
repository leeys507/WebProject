package com.wp.domain.student;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;

import com.wp.domain.student.dto.StudentGetMyBoardDTO;

public interface StudentCustomRepository {
	List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit);
}

@Repository
@Transactional
class StudentCustomRepositoryImpl implements StudentCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	
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
}