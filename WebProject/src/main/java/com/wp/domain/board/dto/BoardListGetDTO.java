package com.wp.domain.board.dto;

import com.wp.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListGetDTO {
    private long bno;
    private String nickname;
    private String title;
    private String register_date;
    private int read_count;
    private int like_count;

    public BoardListGetDTO(Board entity) { 	
        this.bno = entity.getBno();
        this.nickname = entity.getNickname();
        this.title = entity.getTitle();
        this.register_date = entity.getRegister_date();
        this.read_count = entity.getRead_count();
        this.like_count = entity.getLike_count();
    }
}