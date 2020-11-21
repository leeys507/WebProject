package com.wp.controller.lectureevaluation;

import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationGetDTO;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.service.lectureevaluation.LectureEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class LectureEvaluationViewController {
    private final LectureEvaluationService lectureEvaluationService;
    private final HttpSession httpSession;
    @GetMapping("/yu/lectureevaluation/lectureevaluationList")
    public String openLectureEvaluationListView(Model model, Pageable pageable) {
        StudentGetDTO sdata = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        List<LectureEvaluation> leList = lectureEvaluationService.getLectureEvaluationList(sdata.getSid());
        model.addAttribute("lectureList",leList);
        return "lectureevaluation/LectureEvaluationList";
    }
    @GetMapping("/yu/lectureevaluation/EvalLecture")
    public String openLectureEvaluationInsertView(int lno,Model model) {
        StudentGetDTO sdata = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        LectureEvaluationGetDTO le = lectureEvaluationService.getLectureEvaluation(sdata.getSid(),lno);
        model.addAttribute("lecture",le);
        if(le.getSid()==null&&le.getStar()!=0){
            return "errors/errorPage";
        }
        return "lectureevaluation/lectureEvaluationInsert";
    }
}

