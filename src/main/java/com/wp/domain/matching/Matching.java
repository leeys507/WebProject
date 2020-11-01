package com.wp.domain.matching;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
@Entity(name = "matching")
public class Matching {
    @Id
    @Column(name = "bno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bno;
    
    @ManyToOne
    @JoinColumn(name = "request_sid", nullable = false)
    private Student studentForeignkey_request;
    
    @Column(name = "request_nickname", nullable = false, length = 12)
    private String request_nickname;
    
    @ManyToOne
    @JoinColumn(name = "accept_sid")
    private Student studentForeignkey_accept;
    
    @Column(name = "accept_nickname", length = 12)
    private String accept_nickname;
    
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
}
