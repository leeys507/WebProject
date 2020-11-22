package com.wp.service.lectureevaluation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wp.domain.lectureevaluation.LectureEvaluation;

import java.util.List;

public interface LectureEvaluationService {

    boolean insertLectureEvaluation(int lecturenum, String content, int star);

    List<LectureEvaluation> getLectureEvaluationList();

    Page<LectureEvaluation> searchLectureEvaluation(Pageable pageable, String text, String date, String option);
}