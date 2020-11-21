package com.wp.domain.lecture;

import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "lecture")
public class Lecture {
    @Id
    @Column(name = "lno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "lecturenum", nullable = false)
    private int lecturenum;

    @Column(name = "lecturename", nullable = false, length = 20)
    private String lecturename;

    @Column(name = "professor", nullable = false, length = 15)
    private String professor;

    @Column(name = "check_evaluation", nullable = false, length = 2)
    private String check_evaluation;

    @Column(name = "register_date")
    private LocalDateTime register_date;

}
