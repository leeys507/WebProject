package com.wp.service.graph;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wp.domain.graph.GraphCustomRepository;
import com.wp.domain.graph.dto.GraphBoardStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphBoardUsageStatusGetDTO;
import com.wp.domain.graph.dto.GraphMatchingStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphPopularLectureGetDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GraphServiceImpl implements GraphService {
	private final GraphCustomRepository graphCustomRepository;
	
	public GraphBoardStatisticsGetDTO getBoardStatistics(String type) {
		return graphCustomRepository.getBoardStatistics(type);
	}
	
	public List<GraphPopularLectureGetDTO> getPopularLectureList(int syear, int limit) {
		return graphCustomRepository.getPopularLectureList(syear, limit);
	}
	
	public GraphBoardUsageStatusGetDTO getBoardUsageStatus(int syear) {
		return graphCustomRepository.getBoardUsageStatus(syear);
	}
	
	public GraphMatchingStatisticsGetDTO getMatchingStatistics(String type) {
		return graphCustomRepository.getMatchingStatistics(type);
	}
}
