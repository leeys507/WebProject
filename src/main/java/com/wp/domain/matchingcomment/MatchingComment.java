package com.wp.domain.matchingcomment;

import com.wp.domain.matching.Matching;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "matchingcomment")
public class MatchingComment {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno", nullable = false)
    private Matching MatchingForeignkey;

    @Id
    @Column(name = "cno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "nickname", nullable = false, length = 12)
    private String nickname;

    @Column(name = "content", nullable = false, length = 200)
    private String content;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;

    @Column(name = "update_date")
    private LocalDateTime update_date;

    @Column(name = "delete_date")
    private LocalDateTime delete_date;

    @Column(name = "check_delete", nullable = false, length = 2)
    private String check_delete;

    public void update(String content) {
        this.content = content;
        this.update_date = LocalDateTime.now();
    }

    public void delete() {
        this.check_delete = "T";
        this.delete_date = LocalDateTime.now();
    }
    
    public String getRegister_date() {
   		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
   		return formatter.format(this.register_date);
   }
}
