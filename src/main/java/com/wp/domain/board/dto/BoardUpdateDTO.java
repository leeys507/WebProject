package com.wp.domain.board.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardUpdateDTO {
    private String title;
    private String content;
    private String boardtype;
}