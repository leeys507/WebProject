package com.wp.domain.board.dto;

import com.wp.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardListGetDTO {
    private int bno;
    private String nickname;
    private String title;
    private LocalDateTime register_date;
    private int read_count;
    private Integer like_count;

    public  BoardListGetDTO(Board entity){
        this.bno = entity.getBno();
        this.nickname = entity.getNickname();
        this.title = entity.getTitle();
        this.register_date = entity.getRegister_date();
        this.read_count = entity.getRead_count();
        this.like_count = entity.getLike_count();
    }
}