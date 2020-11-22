package com.wp.service.lectureevaluation;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.lecture.LectureRepository;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.LectureEvaluationRepository;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationInsertDTO;
import com.wp.domain.student.dto.StudentGetDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    public Page<LectureEvaluation> searchLectureEvaluation(Pageable pageable, String text, String date, String option) {
        String addQuery = "";
        int dateNum = 0;

        if (option.equals("title")) {
            addQuery = "lecturename like ?1";
            text = "%" + text + "%";
        } else if (option.equals("professor")) {
            addQuery = "professor like ?1";
            text = "%" + text + "%";
        } else if (option.equals("content")) {
            addQuery = "match(content) against(?1 in boolean mode)";
        } else if (option.equals("writer")) {
            addQuery = "nickname like ?1";
            text = "%" + text + "%";
        }

        if (date.equals("all")) {
            return lectureEvaluationRepository.searchLectureEvaluationOptions(addQuery, text, PageRequest.of(pageable.getPageNumber(), 10));
        } else {
            if (date.equals("1week"))
                dateNum = 7;
            else if (date.equals("1month"))
                dateNum = 30;
            else if (date.equals("6month"))
                dateNum = 180;
        }
        return lectureEvaluationRepository.searchLectureEvaluationOptionsAndDate(addQuery, text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
    }
    public List<LectureEvaluation> getLectureEvaluationList() {
            return lectureEvaluationRepository.findByList();
    }
}
