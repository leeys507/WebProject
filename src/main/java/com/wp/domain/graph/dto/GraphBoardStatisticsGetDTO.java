package com.wp.domain.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraphBoardStatisticsGetDTO {
	long total_count;
	long free_count;
	long used_count;
	long knowledge_count;
	long question_count;
}
