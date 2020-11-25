package com.wp.domain.student.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentMyInfoDataGetDTO {
	int likeCount;
	int commentCount;
	int boardCount;
	List<String> searchWordList;
}
