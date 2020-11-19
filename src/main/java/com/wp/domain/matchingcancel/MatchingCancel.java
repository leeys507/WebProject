package com.wp.domain.matchingcancel;

import com.wp.domain.matching.Matching;
import com.wp.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "matchingcancel")
public class MatchingCancel {
    @Id
    @Column(name = "cno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno", nullable = false)
    private Matching MatchingForeignkey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "content", nullable = false, length = 200)
    private String content;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;
}
