package com.wp.domain.board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

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
	@JoinColumns(value ={
	@JoinColumn(name = "nickname", nullable = false), @JoinColumn(name = "sid", nullable = false) })
	private Student foreignkey;
	
	@Column(name = "boardtype", nullable = false, length = 12)
	private String boardtype;
	
	@Column(name = "title", nullable = false, length = 150)
	private String title;
	
	@Lob
	@Column(name = "content", nullable = false)		//text
	private String content;
	
	@CreatedDate
	@Column(name = "regdate", nullable = false, length = 20)
	private LocalDateTime regdate;
	
	@Column(name = "readcount", nullable = false)
	private int readcount;
	
	@Column(name = "imagepath")
	private String imagepath;
	
	@Column(name = "likecount", nullable = false)
	private Integer likecount;
}