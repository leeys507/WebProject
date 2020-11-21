package com.wp.domain.searchword;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.wp.domain.searchword.dto.SearchWordGetDTO;

public interface SearchWordCustomRepository {
	public List<SearchWordGetDTO> getWordRanking(int limit);
}

@Repository
@Transactional
class SearchWordCustomRepositoryImpl implements SearchWordCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<SearchWordGetDTO> getWordRanking(int limit) {
		String sql = "select thistime.word as word, ifnull(cast(prevtime.rownum as signed)-cast(thistime.rownum as signed), 20) as rankingchange " +
				"from ( " +
				 "select row_number() over (order by count(word) desc) as rownum, word, count(word) " +
				 "from searchword " + 
				 "where TIMESTAMPDIFF(HOUR, register_date, SYSDATE()) <= 1 " +
				 "GROUP BY word " +
				 "ORDER BY count(word) desc limit ?1 " +
				 ") as thistime " +
				 "left outer join" +
				 "( " +
				 "select row_number() over (order by count(word) desc) as rownum, word, count(word) " +
				 "from searchword " +
				 "where TIMESTAMPDIFF(HOUR, register_date, SYSDATE()) <= 3 and TIMESTAMPDIFF(HOUR, register_date, SYSDATE()) >= 2 " +
				 "GROUP BY word " +
				 "ORDER BY count(word) desc limit ?2 " +
				 ") as prevtime " +
				 "on thistime.word = prevtime.word";
		
	    Query query = null;
		
	    query = entityManager.createNativeQuery(sql, "SearchWordGetDTOMapping");
	    query.setParameter(1, limit);
	    query.setParameter(2, limit);
	    
	    @SuppressWarnings("unchecked")
	    List<SearchWordGetDTO> list = query.getResultList();
	    
	    return list;
	}
}