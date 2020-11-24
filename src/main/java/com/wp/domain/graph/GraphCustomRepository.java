package com.wp.domain.graph;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.wp.domain.graph.dto.GraphBoardStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphBoardUsageStatusGetDTO;
import com.wp.domain.graph.dto.GraphMatchingStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphPopularLectureGetDTO;

public interface GraphCustomRepository {
	GraphBoardStatisticsGetDTO getBoardStatistics(String type);
	List<GraphPopularLectureGetDTO> getPopularLectureList(int syear, int limit);
	GraphBoardUsageStatusGetDTO getBoardUsageStatus(int syear);
	GraphMatchingStatisticsGetDTO getMatchingStatistics(String type);
}

@Repository
@Transactional
class GraphCustomRepositoryImpl implements GraphCustomRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public GraphBoardStatisticsGetDTO getBoardStatistics(String type) {
		GraphBoardStatisticsGetDTO data = new GraphBoardStatisticsGetDTO();
		String sql = null;
		Query query = null;
		
		switch(type) {
		case "boardCount":
			sql = "select count(*) from board where check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setTotal_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from board where boardtype = '자유게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setFree_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from board where boardtype = '중고게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setUsed_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from board where boardtype = '정보게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setKnowledge_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from board where boardtype = '질문게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setQuestion_count(((BigInteger)query.getSingleResult()).longValue());
			
			return data;
			
		case "writerCount":
			sql = "select count(*) from (select distinct sid from board where check_delete = 'F') as t";
			query = entityManager.createNativeQuery(sql);
			data.setTotal_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from (select distinct sid from board where boardtype = '자유게시판' and check_delete = 'F') as t";
			query = entityManager.createNativeQuery(sql);
			data.setFree_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from (select distinct sid from board where boardtype = '중고게시판' and check_delete = 'F') as t";
			query = entityManager.createNativeQuery(sql);
			data.setUsed_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from (select distinct sid from board where boardtype = '정보게시판' and check_delete = 'F') as t";
			query = entityManager.createNativeQuery(sql);
			data.setKnowledge_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from (select distinct sid from board where boardtype = '질문게시판' and check_delete = 'F') as t";
			query = entityManager.createNativeQuery(sql);
			data.setQuestion_count(((BigInteger)query.getSingleResult()).longValue());
			
			return data;
			
		case "likeCount":
			sql = "select sum(like_count) from board where check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setTotal_count(((BigDecimal)query.getSingleResult()).longValue());
			
			sql = "select sum(like_count) from board where boardtype = '자유게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setFree_count(((BigDecimal)query.getSingleResult()).longValue());
			
			sql = "select sum(like_count) from board where boardtype = '중고게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setUsed_count(((BigDecimal)query.getSingleResult()).longValue());
			
			sql = "select sum(like_count) from board where boardtype = '정보게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setKnowledge_count(((BigDecimal)query.getSingleResult()).longValue());
			
			sql = "select sum(like_count) from board where boardtype = '질문게시판' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setQuestion_count(((BigDecimal)query.getSingleResult()).longValue());
			
			return data;
			
		case "commentCount":
			sql = "select count(*) from boardcomment where bno in (select bno from board where check_delete = 'F')";
			query = entityManager.createNativeQuery(sql);
			data.setTotal_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from boardcomment where bno in (select bno from board where boardtype = '자유게시판' and check_delete = 'F')";
			query = entityManager.createNativeQuery(sql);
			data.setFree_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from boardcomment where bno in (select bno from board where boardtype = '중고게시판' and check_delete = 'F')";
			query = entityManager.createNativeQuery(sql);
			data.setUsed_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from boardcomment where bno in (select bno from board where boardtype = '정보게시판' and check_delete = 'F')";
			query = entityManager.createNativeQuery(sql);
			data.setKnowledge_count(((BigInteger)query.getSingleResult()).longValue());
			
			sql = "select count(*) from boardcomment where bno in (select bno from board where boardtype = '질문게시판' and check_delete = 'F')";
			query = entityManager.createNativeQuery(sql);
			data.setQuestion_count(((BigInteger)query.getSingleResult()).longValue());
			
			return data;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<GraphPopularLectureGetDTO> getPopularLectureList(int syear, int limit) {
		String sql = null;
		Query query = null;
		
		List<GraphPopularLectureGetDTO> data = null;

		sql = "select lecturename, avg(star) as avgstar from lectureevaluation where sid in (select sid from student where syear = " + syear + ") group by lecturename order by avgstar desc limit " + limit;
		query = entityManager.createNativeQuery(sql, "GraphPopularLectureGetDTOMapping");
			
		data = query.getResultList();
		return data;
	}
	
	public GraphBoardUsageStatusGetDTO getBoardUsageStatus(int syear) {
		GraphBoardUsageStatusGetDTO data = new GraphBoardUsageStatusGetDTO();
		String sql = null;
		Query query = null;
		
		sql = "select count(*) from student where sid in (select distinct sid from board where boardtype = '자유게시판' and check_delete = 'F') and syear = " + syear;
		query = entityManager.createNativeQuery(sql);
		data.setFree_count(((BigInteger)query.getSingleResult()).longValue());
		
		sql = "select count(*) from student where sid in (select distinct sid from board where boardtype = '중고게시판' and check_delete = 'F') and syear = " + syear;
		query = entityManager.createNativeQuery(sql);
		data.setUsed_count(((BigInteger)query.getSingleResult()).longValue());
		
		sql = "select count(*) from student where sid in (select distinct sid from board where boardtype = '정보게시판' and check_delete = 'F') and syear = " + syear;
		query = entityManager.createNativeQuery(sql);
		data.setKnowledge_count(((BigInteger)query.getSingleResult()).longValue());
		
		sql = "select count(*) from student where sid in (select distinct sid from board where boardtype = '질문게시판' and check_delete = 'F') and syear = " + syear;
		query = entityManager.createNativeQuery(sql);
		data.setQuestion_count(((BigInteger)query.getSingleResult()).longValue());
		
		return data;
	}
	
	public GraphMatchingStatisticsGetDTO getMatchingStatistics(String type) {
		GraphMatchingStatisticsGetDTO data = new GraphMatchingStatisticsGetDTO();
		String sql = null;
		Query query = null;
		
		switch(type) {
		case "avgMoney":
			sql = "select avg(money) from matching where check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setTotal_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setTotal_count(0);
			}
			
			sql = "select avg(money) from matching where boardtype = '청소' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setCleaning_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setCleaning_count(0);
			}
			
			sql = "select avg(money) from matching where boardtype = '배달' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setDelivery_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setDelivery_count(0);
			}
			
			sql = "select avg(money) from matching where boardtype = '역할대행' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setRole_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setRole_count(0);
			}
			
			sql = "select avg(money) from matching where boardtype = '심부름' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setErrand_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setErrand_count(0);
			}
			
			return data;
			
		case "successPercentage":
			sql = "select (count(*) / total ) * 100 as Percentege "
				+ "from matching, "
				+ "(select count(*) as total "
				+ "from matching where check_delete = 'F') as myTotal where check_success = 'T' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setTotal_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setTotal_count(0);
			}
			
			sql = "select (count(*) / total ) * 100 as Percentege "
					+ "from matching, "
					+ "(select count(*) as total "
					+ "from matching where boardtype = '청소' and check_delete = 'F') as myTotal where boardtype = '청소' and check_success = 'T' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setCleaning_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setCleaning_count(0);
			}
			
			sql = "select (count(*) / total ) * 100 as Percentege "
					+ "from matching, "
					+ "(select count(*) as total "
					+ "from matching where boardtype = '배달' and check_delete = 'F') as myTotal where boardtype = '배달' and check_success = 'T' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setDelivery_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setDelivery_count(0);
			}
			
			sql = "select (count(*) / total ) * 100 as Percentege "
					+ "from matching, "
					+ "(select count(*) as total "
					+ "from matching where boardtype = '역할대행' and check_delete = 'F') as myTotal where boardtype = '역할대행' and check_success = 'T' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setRole_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setRole_count(0);
			}
			
			sql = "select (count(*) / total ) * 100 as Percentege "
					+ "from matching, "
					+ "(select count(*) as total "
					+ "from matching where boardtype = '심부름' and check_delete = 'F') as myTotal where boardtype = '심부름' and check_success = 'T' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			if(query.getSingleResult() != null) {
				data.setErrand_count(((BigDecimal)query.getSingleResult()).doubleValue());
			}
			else {
				data.setErrand_count(0);
			}
			
			return data;
			
		case "recentlyRegistered":
			sql = "select count(*) from matching where register_date > date_sub(now(), interval 7 day) and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setTotal_count(((BigInteger)query.getSingleResult()).doubleValue());
			
			sql = "select count(*) from matching where register_date > date_sub(now(), interval 7 day) and boardtype = '청소' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setCleaning_count(((BigInteger)query.getSingleResult()).doubleValue());
			
			sql = "select count(*) from matching where register_date > date_sub(now(), interval 7 day) and boardtype = '배달' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setDelivery_count(((BigInteger)query.getSingleResult()).doubleValue());
			
			sql = "select count(*) from matching where register_date > date_sub(now(), interval 7 day) and boardtype = '역할대행' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setRole_count(((BigInteger)query.getSingleResult()).doubleValue());
			
			sql = "select count(*) from matching where register_date > date_sub(now(), interval 7 day) and boardtype = '심부름' and check_delete = 'F'";
			query = entityManager.createNativeQuery(sql);
			data.setErrand_count(((BigInteger)query.getSingleResult()).doubleValue());
			
			return data;
		}
		return null;
	}
}