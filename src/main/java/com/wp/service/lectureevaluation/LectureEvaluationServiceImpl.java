package com.wp.service.lectureevaluation;
import com.wp.domain.lecture.Lecture;
import com.wp.domain.lecture.LectureRepository;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.LectureEvaluationRepository;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationInsertDTO;
import com.wp.domain.student.dto.StudentGetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureEvaluationServiceImpl implements LectureEvaluationService {
    private final LectureEvaluationRepository lectureEvaluationRepository;
    private final LectureRepository lectureRepository;
    private final HttpSession session;
    public List<LectureEvaluation> getLectureEvaluationList(String sid) {
        return lectureEvaluationRepository.findBySid(sid);
    }



    @Transactional
    public boolean insertLectureEvaluation(int lecturenum, String content,int star) {
        StudentGetDTO sdata = (StudentGetDTO) session.getAttribute("studentInfo");
        Lecture ldata=lectureRepository.findBySidAndLecturenum(sdata.getSid(),lecturenum);
        ldata.setCheck_evaluation("T");
        if(lectureEvaluationRepository.existsBySidAndLecturenum(sdata.getSid(),lecturenum)!=0){
            return false;
        }
        LectureEvaluationInsertDTO ledata=new LectureEvaluationInsertDTO(ldata,content,star);
        lectureEvaluationRepository.save(ledata.toEntity());
        return true;
    }
}
