package com.wp.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
	int status;
    T data;
}
/*
 * 이 클래스를 만든 이유는 마치 c언에서 구조체처럼 클라이언트의 요청에 따라 그 요청의 결과가 어떤지 상태와 데이터를 담아서 알아보기 쉽게
 * 해줄려고 이런식으로 return 값을 text값이 아닌 클래스로 데이터를 담아서 하나의 구조체처럼 json형태로 그 결과를 알려주려고 이렇게보내는듯?
 *
 * 결국 응답을 object로 주면 브라우저에선 json형태로 보여줄테니깐 json형태는 위 클래스의 모든 데이터를 알아보기 쉽게 만들어주니깐
 *
 * */