package com.wp.domain.report;

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
@Entity(name = "report")
public class Report  {
    @Id
    @Column(name = "idx", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid", nullable = false)
    private Student studentForeignkey;

    @Column(name = "typename", nullable = false, length = 15)
    private String typename;
    
    @Column(name = "typenumber", nullable = false, columnDefinition = "int")
    private long typenumber;
    
    @Column(name = "reason", nullable = false, length = 100)
    private String reason;
    
    @Column(name = "register_date")
    private LocalDateTime register_date;
    
    public String getRegister_date() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	return formatter.format(this.register_date);
    }
}
