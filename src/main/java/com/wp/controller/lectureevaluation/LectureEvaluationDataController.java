package com.wp.controller.lectureevaluation;

import com.wp.domain.lectureevaluation.dto.LectureEvaluationInsertDTO;
import com.wp.service.lectureevaluation.LectureEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LectureEvaluationDataController {
    private final LectureEvaluationService lectureEvaluationService;


    @PostMapping("/yu/lectureEvaluationInsert/")
    public boolean insertLectureEvaluation(int lecturenum, String content, int star) {
        return lectureEvaluationService.insertLectureEvaluation(lecturenum,content,star);
    }
}
