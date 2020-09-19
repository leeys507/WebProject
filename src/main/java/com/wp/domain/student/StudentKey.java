package com.wp.domain.student;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class StudentKey implements Serializable {
	private String sid;
    private String nickname;
}