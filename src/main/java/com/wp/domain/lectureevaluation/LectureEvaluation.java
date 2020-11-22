package com.wp.domain.lectureevaluation;

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
@Entity(name = "lectureevaluation")
public class LectureEvaluation {
    @Id
    @Column(name = "lno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "nickname", nullable = false, length = 12)
    private String nickname;

    @Column(name = "lecturenum", nullable = false)
    private int lecturenum;

    @Column(name = "lecturename", nullable = false, length = 20)
    private String lecturename;

    @Column(name = "content",nullable = false, length = 500)
    private String content;

    @Column(name = "professor", nullable = false, length = 15)
    private String professor;

    @Column(name = "star",nullable = false)
    private int star;

    @Column(name = "check_delete", nullable = false, length = 2)
    private String check_delete;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;

    public String getRegister_date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
        return formatter.format(this.register_date);
    }
}
