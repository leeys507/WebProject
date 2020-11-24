package com.wp.controller.graph;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.graph.dto.GraphBoardStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphBoardUsageStatusGetDTO;
import com.wp.domain.graph.dto.GraphMatchingStatisticsGetDTO;
import com.wp.domain.graph.dto.GraphPopularLectureGetDTO;
import com.wp.service.graph.GraphService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GraphDataController {
	private final GraphService graphService;
	
    @PostMapping("/yu/getBoardStatistics")
    public GraphBoardStatisticsGetDTO getBoardStatistics(String type) throws Exception {
        return graphService.getBoardStatistics(type);
    }
    
    @PostMapping("/yu/getPopularLectureList")
    public List<GraphPopularLectureGetDTO> getPopularLectureList(int syear, int limit) throws Exception {
        return graphService.getPopularLectureList(syear, limit);
    }
    
    @PostMapping("/yu/getBoardUsageStatus")
    public GraphBoardUsageStatusGetDTO getBoardUsageStatus(int syear) throws Exception {
        return graphService.getBoardUsageStatus(syear);
    }
    
    @PostMapping("/yu/getMatchingStatistics")
    public GraphMatchingStatisticsGetDTO getMatchingStatistics(String type) throws Exception {
        return graphService.getMatchingStatistics(type);
    }
}
