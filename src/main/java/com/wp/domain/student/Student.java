package com.wp.domain.student;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "student")
@IdClass(StudentKey.class)
public class Student extends BaseTimeEntity {
	@Id
	@Column(name = "sid", length = 8)
	private String sid;
	
	@Id
	@Column(name = "nickname", length = 12)
	private String nickname;
	
	@Column(name = "gen", nullable = false, length = 2)
	private String gen;
	
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@Column(name = "syear", nullable = false)
	private int syear;
	
	@Column(name = "department", nullable = false, length = 20)
	private String department;
	
	@Column(name = "point", nullable = false)
	private int point;
	
	@Column(name = "exp", nullable = false)
	private int exp;
	
	@Column(name = "fboard")
	private Integer fboard;
}

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class BaseTimeEntity {
    @CreatedDate
    @Column(name = "flogin", nullable = false)
    private LocalDateTime flogin;
    @LastModifiedDate
    private LocalDateTime updateTime;
}