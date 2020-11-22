package com.wp.controller.lectureevaluation;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.lecture.dto.LectureGetDTO;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.service.lecture.LectureService;
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
    private final LectureService lectureService;
    private final HttpSession httpSession;
    @GetMapping("/yu/lectureevaluation/lectureevaluationList")
    public String openLectureEvaluationListView(Model model, Pageable pageable) {
        StudentGetDTO sdata = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        List<Lecture> lList = lectureService.getLectureList(sdata.getSid());
        List<LectureEvaluation> leList=lectureEvaluationService.getLectureEvaluationList();
        model.addAttribute("lectureList",lList);
        model.addAttribute("lectureEvaluationList",leList);
        return "lectureevaluation/LectureEvaluationList";
    }
    @GetMapping("/yu/lectureevaluation/EvalLecture")
    public String openLectureEvaluationInsertView(int lno,Model model) {
        StudentGetDTO sdata = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        LectureGetDTO le = lectureService.getLecture(sdata.getSid(),lno);
        model.addAttribute("lecture",le);
        if(le.getCheck_evaluation()=="T"&&le==null){
            return "errors/errorPage";
        }
        return "lectureevaluation/lectureEvaluationInsert";
    }
}

