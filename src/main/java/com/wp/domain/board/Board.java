package com.wp.domain.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.wp.domain.boardcomment.BoardComment;
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
    @Column(name = "bno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bno;

    @ManyToOne
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "nickname", nullable = false, length = 12)
    private String nickname;
    
    @Column(name = "boardtype", nullable = false, length = 12)
    private String boardtype;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)      //text
    private String content;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;

    @Column(name = "read_count", nullable = false)
    private int read_count;

    @Column(name = "image_path", length = 300)
    private String image_path;

    @Column(name = "like_count", nullable = false)
    private Integer like_count;
    
    @Column(name = "update_date")
    private LocalDateTime update_date;

    @Column(name = "delete_date")
    private LocalDateTime delete_date;

    @Column(name = "check_delete", nullable = false, length = 2)
    private String check_delete;

    @OneToMany(mappedBy = "boardForeignkey", targetEntity= BoardComment.class)
    private List<BoardComment> boardList = new ArrayList<BoardComment>();

    public String getRegister_date() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return formatter.format(this.register_date);
    }
    
    public String getUpdate_date() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return (update_date == null) ? null : formatter.format(this.update_date);
    }
    
    public void update(String title, String content,String boardtype) {
        this.title=title;
        this.content=content;
        this.boardtype=boardtype;
        this.register_date=LocalDateTime.now();
    }

    public void delete() {
        this.check_delete = "T";
        this.delete_date = LocalDateTime.now();
    }
    public void updatelike(int like_count){
        this.like_count=this.like_count+like_count;
    }
}