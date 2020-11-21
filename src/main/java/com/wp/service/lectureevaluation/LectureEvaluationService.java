package com.wp.service.lectureevaluation;

import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationGetDTO;

import java.util.List;

public interface LectureEvaluationService {
    List<LectureEvaluation> getLectureEvaluationList(String Sid);

    boolean insertLectureEvaluation() throws InterruptedException;

    LectureEvaluationGetDTO getLectureEvaluation(String sid, int lno);

    boolean updateLectureEvaluation(long lno, String content, int star);
}
