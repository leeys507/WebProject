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
		String sql = "select thistime.word as word, ifnull(cast(prevtime.rownum as signed)-cast(thistime.rownum as signed), 20) as rankingchange "
				+ "from ( "
				+ "select row_number() over (order by count(*) desc) as rownum, word "
				+ "from searchword "
				+ "where register_date > date_sub(now(), interval 1 hour) "
				+ "group by word "
				+ "having count(*) >= 5 "
				+ "limit ?1 "
				+ ") as thistime "
				+ "left outer join"
				+ "( "
				+ "select row_number() over (order by count(*) desc) as rownum, word "
				+ "from searchword "
				+ "where register_date > date_sub(now(), interval 2 hour) and register_date <= date_sub(now(), interval 1 hour) "
				+ "group by word "
				+ "having count(*) >= 5 "
				+ "limit ?2 "
				+ ") as prevtime "
				+ "on thistime.word = prevtime.word";
		
	    Query query = null;
		
	    query = entityManager.createNativeQuery(sql, "SearchWordGetDTOMapping");
	    query.setParameter(1, limit);
	    query.setParameter(2, limit);
	    
	    @SuppressWarnings("unchecked")
	    List<SearchWordGetDTO> list = query.getResultList();
	    
	    return list;
	}
}