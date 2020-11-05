package com.wp.domain.boardlike;

import com.wp.domain.board.Board;
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
@Entity(name = "boardlike")
public class BoardLike  {
    @Id
    @Column(name = "lno", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lno;

    @ManyToOne
    @JoinColumn(name = "bno", nullable = false)
    private Board boardForeignkey;

    @ManyToOne
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "check_like", nullable = false, length = 2)
    private String check_like;
    public void update(String check_like) {
        this.check_like=check_like;
    }
}
