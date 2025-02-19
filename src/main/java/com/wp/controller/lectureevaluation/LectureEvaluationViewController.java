package com.wp.controller.lectureevaluation;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.lecture.dto.LectureGetDTO;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationGetDTO;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.service.lecture.LectureService;
import com.wp.service.lectureevaluation.LectureEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        Page<LectureEvaluation> page = lectureEvaluationService.findLectureEvaluations(pageable);
        model.addAttribute("lectureList", page);
        return "lectureevaluation/lectureEvaluationList";
    }

    @GetMapping("/yu/lectureevaluation/lectureevaluation")
    public String openLectureEvaluation(Model model) {
        StudentGetDTO sdata = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        List<Lecture> lList = lectureService.getLectureList(sdata.getSid());
        List<LectureEvaluation> leList=lectureEvaluationService.getLectureEvaluationList();
        model.addAttribute("lectureList",lList);
        model.addAttribute("lectureEvaluationList",leList);
        return "lectureevaluation/lectureEvaluation";
    }

    @GetMapping("/yu/lectureevaluation/EvalLecture")
    public String openLectureEvaluationInsertView(int lno,Model model) {
        StudentGetDTO sdata = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        LectureGetDTO ldata = lectureService.getLecture(sdata.getSid(),lno);
        model.addAttribute("lecture",ldata);
        if(ldata.getCheck_evaluation()=="T" && ldata==null){
            return "errors/errorPage";
        }
        return "lectureevaluation/lectureEvaluationInsert";
    }
    @GetMapping("/yu/lectureevaluation/ViewLectureEvaluation/{lno}")
    public String openLectureEvaluationView(@PathVariable long lno, Model model) {
        LectureEvaluationGetDTO ledata=lectureEvaluationService.findByLno(lno);
        model.addAttribute("lectureEvaluation",ledata);
        return "lectureevaluation/lectureEvaluationView";
    }
}

