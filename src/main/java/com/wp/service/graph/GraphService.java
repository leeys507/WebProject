package com.wp.service.graph;

import java.util.List;

import com.wp.domain.graph.dto.GraphBoardStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphBoardUsageStatusGetDTO;
import com.wp.domain.graph.dto.GraphMatchingStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphPopularLectureGetDTO;

public interface GraphService {
	GraphBoardStatisticsGetDTO getBoardStatistics(String type);
	List<GraphPopularLectureGetDTO> getPopularLectureList(int syear, int limit);
	GraphBoardUsageStatusGetDTO getBoardUsageStatus(int syear);
	GraphMatchingStatisticsGetDTO getMatchingStatistics(String type);
}
