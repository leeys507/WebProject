package com.wp.domain.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.wp.domain.board.Board;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.matching.Matching;
import com.wp.domain.student.dto.StudentGetMyBoardDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SqlResultSetMapping(
        name="StudentGetMyBoardDTOMapping",
        classes = @ConstructorResult(
                targetClass = StudentGetMyBoardDTO.class,
                columns = {
                        @ColumnResult(name="title", type = String.class),
                        @ColumnResult(name="bno", type = Long.class),
                        @ColumnResult(name="register_date", type = LocalDateTime.class),
                        @ColumnResult(name="check_delete", type = String.class),
                        @ColumnResult(name="type", type = String.class),
                        @ColumnResult(name="boardtype", type = String.class),
                })
)

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "student")
public class Student {
   @Id
   @Column(name = "sid", length = 8)
   private String sid;

   @Column(name = "nickname", length = 12, unique = true)
   private String nickname;

   @Column(name = "phonenum", length = 11, nullable = false)
   private String phonenum;

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

   @Column(name = "first_login", nullable = false)
   private LocalDateTime first_login;

   @Column(name = "update_date")
   private LocalDateTime update_date;

   @OneToMany(mappedBy = "studentForeignkey", targetEntity= Board.class)
   private List<Board> boardList = new ArrayList<Board>();

   @OneToMany(mappedBy = "studentForeignkey", targetEntity= BoardComment.class)
   private List<BoardComment> boardCommentList = new ArrayList<BoardComment>();
   
   @OneToMany(mappedBy = "studentForeignkey_request", targetEntity= Matching.class)
   private List<Matching> matchingList_request = new ArrayList<Matching>();
   
   @OneToMany(mappedBy = "studentForeignkey_accept", targetEntity= Matching.class)
   private List<Matching> matchingList_accept = new ArrayList<Matching>();
   
   public String getFirst_login() {
   		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
   		return formatter.format(this.first_login);
   }
   
   public String getUpdate_date() {
	   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
	   	return (update_date == null) ? null : formatter.format(this.update_date);
   }
}