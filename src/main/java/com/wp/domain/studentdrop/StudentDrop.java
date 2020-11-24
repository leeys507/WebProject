package com.wp.domain.studentdrop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "studentdrop")
public class StudentDrop {
    @Id
    @Column(name = "sid", length = 8)
    private String sid;
}
