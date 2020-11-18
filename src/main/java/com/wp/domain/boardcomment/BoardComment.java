package com.wp.domain.boardcomment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno", nullable = false)
    private Board boardForeignkey;
    
    @Id
    @Column(name = "cno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cno;
    
    @Column(name = "ccno", columnDefinition = "int")
    private long ccno;
    
    @Column(name = "group_id", nullable = false, columnDefinition = "int")
    private long group_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "nickname", nullable = false, length = 12)
    private String nickname;

    @Column(name = "content", nullable = false, length = 200)
    private String content;
    
    @Column(name = "tonickname", length = 12)
    private String tonickname;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;
    
    @Column(name = "update_date")
    private LocalDateTime update_date;
    
    @Column(name = "delete_date")
    private LocalDateTime delete_date;
    
    @Column(name = "check_delete", nullable = false, length = 2)
    private String check_delete;
    
    public String getRegister_date() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return formatter.format(this.register_date);
    }
    
    public String getUpdate_date() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return (update_date == null) ? null : formatter.format(this.update_date);
    }
    
    public String getDelete_date() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return (delete_date == null) ? null : formatter.format(this.delete_date);
    }
    
    public void update(String content) {
    	this.content = content;
        this.update_date = LocalDateTime.now();
    }
    
    public void delete() {
    	this.check_delete = "T";
        this.delete_date = LocalDateTime.now();
    }
}