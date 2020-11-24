package com.wp.domain.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GraphPopularLectureGetDTO {
	String lecturename;
	double avgstar;
}
