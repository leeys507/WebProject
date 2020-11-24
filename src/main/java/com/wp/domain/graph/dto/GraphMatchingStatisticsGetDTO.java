package com.wp.domain.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GraphMatchingStatisticsGetDTO {
	double total_count;
	double cleaning_count;
	double delivery_count;
	double role_count;
	double errand_count;
}
