package com.wp.controller.admin;

import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.service.lecture.LectureService;
import com.wp.service.lectureevaluation.LectureEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminLectureViewController {
    private final LectureEvaluationService lectureEvaluationService;
    @GetMapping("/yu/admin/lectureevaluationList")
    public String openLectureEvaluationListView(Model model, Pageable pageable) {
        Page<LectureEvaluation> page = lectureEvaluationService.findLectureEvaluations(pageable);
        model.addAttribute("lectureList", page);
        return "admin/adminLectureEvaluationList";
    }
}
