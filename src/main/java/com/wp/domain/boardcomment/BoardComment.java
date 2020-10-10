package com.wp.domain.boardcomment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wp.domain.board.Board;
import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "boardcomment")
public class BoardComment {
    @ManyToOne
    @JoinColumn(name = "bno", nullable = false)
    private Board boardForeignkey;
    
    @Id
    @Column(name = "cno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cno;
    
    @Column(name = "ccno", columnDefinition = "int")
    private long ccno;

    @ManyToOne
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "nickname", nullable = false, length = 12)
    private String nickname;

    @Column(name = "content", nullable = false, length = 200)
    private String content;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;
    
    @Column(name = "update_time")
    private LocalDateTime update_time;
    
    public String getRegister_date() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return formatter.format(this.register_date);
    }
    
    public String getUpdate_time() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return formatter.format(this.update_time);
    }
}