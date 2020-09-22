package com.wp.domain.student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.wp.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

   @Column(name = "update_time", nullable = false)
   private LocalDateTime update_time;

   @OneToMany(mappedBy = "foreignkey", targetEntity= Board.class)
   private List<Board> boardList = new ArrayList<Board>();

}