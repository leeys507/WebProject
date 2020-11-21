package com.wp.service.lecture;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.lecture.dto.LectureGetDTO;

import java.util.List;

public interface LectureService {
    boolean insertLecture() throws InterruptedException;

    List<Lecture> getLectureList(String sid);

    LectureGetDTO getLecture(String sid, int lno);
}
