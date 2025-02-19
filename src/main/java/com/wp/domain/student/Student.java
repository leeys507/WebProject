package com.wp.domain.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.wp.domain.board.Board;
import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.boardlike.BoardLike;
import com.wp.domain.lecture.Lecture;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.matching.Matching;
import com.wp.domain.searchword.SearchWord;
import com.wp.domain.student.dto.StudentGetMyBoardDTO;
import com.wp.domain.student.dto.StudentGetMyCommentDTO;

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
                        @ColumnResult(name="type", type = String.class),
                        @ColumnResult(name="boardtype", type = String.class),
                })
)

@SqlResultSetMapping(
        name="StudentGetMyCommentDTOMapping",
        classes = @ConstructorResult(
                targetClass = StudentGetMyCommentDTO.class,
                columns = {
                        @ColumnResult(name="content", type = String.class),
                        @ColumnResult(name="bno", type = Long.class),
                        @ColumnResult(name="register_date", type = LocalDateTime.class),
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

   @OneToMany(mappedBy = "studentForeignkey", targetEntity = Board.class)
   private List<Board> boardList = new ArrayList<Board>();

   @OneToMany(mappedBy = "studentForeignkey", targetEntity = BoardComment.class)
   private List<BoardComment> boardCommentList = new ArrayList<BoardComment>();
   
   @OneToMany(mappedBy = "studentForeignkey_request", targetEntity = Matching.class)
   private List<Matching> matchingList_request = new ArrayList<Matching>();
   
   @OneToMany(mappedBy = "studentForeignkey_accept", targetEntity = Matching.class)
   private List<Matching> matchingList_accept = new ArrayList<Matching>();
   
   @OneToMany(mappedBy = "studentForeignkey", targetEntity = BoardLike.class)
   private List<BoardLike> boardLikeList = new ArrayList<BoardLike>();

   @OneToMany(mappedBy = "studentForeignkey", targetEntity = LectureEvaluation.class)
   private List<LectureEvaluation> lectureEvaluationList = new ArrayList<LectureEvaluation>();

   @OneToMany(mappedBy = "studentForeignkey", targetEntity = Lecture.class)
   private List<Lecture> lectureList = new ArrayList<Lecture>();
   
   @OneToMany(mappedBy = "studentForeignkey", targetEntity = SearchWord.class)
   private List<SearchWord> searchWordList = new ArrayList<SearchWord>();
   
   public String getFirst_login() {
   		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
   		return formatter.format(this.first_login);
   }
   
   public String getUpdate_date() {
	   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
	   	return (update_date == null) ? null : formatter.format(this.update_date);
   }
   
   public LocalDateTime getUpdate_dateLocalDate() {
	   return update_date;
  }
}