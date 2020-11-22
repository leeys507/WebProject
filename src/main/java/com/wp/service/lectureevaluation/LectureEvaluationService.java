package com.wp.service.lectureevaluation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.lectureevaluation.LectureEvaluation;

public interface LectureEvaluationService {

    boolean insertLectureEvaluation(int lecturenum, String content,int star);
    Page<LectureEvaluation> searchLectureEvaluation(Pageable pageable, String text, String date, String option);
}
