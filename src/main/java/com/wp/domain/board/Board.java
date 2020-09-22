package com.wp.domain.board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "board")
public class Board {
    @Id
    @Column(name = "bno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    @ManyToOne
    @JoinColumn(name = "sid", nullable = false)
    private Student foreignkey;

    @Column(name = "nickname", nullable = false, length = 12)
    private String nickname;
    
    @Column(name = "board_type", nullable = false, length = 12)
    private String board_type;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)      //text
    private String content;

    @Column(name = "register_date", nullable = false, length = 20)
    private LocalDateTime register_date;

    @Column(name = "read_count", nullable = false)
    private int read_count;

    @Column(name = "image_path", length = 300)
    private String image_path;

    @Column(name = "like_count", nullable = false)
    private Integer like_count;
    
    @Column(name = "update_time")
    private LocalDateTime update_time;

    public void update(String title, String content,String boardtype) {
        this.title=title;
        this.content=content;
        this.board_type=boardtype;
        this.register_date=LocalDateTime.now();
    }
}