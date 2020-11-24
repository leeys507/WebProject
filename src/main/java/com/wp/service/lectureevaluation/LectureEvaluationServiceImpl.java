package com.wp.service.lectureevaluation;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.lecture.LectureRepository;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.LectureEvaluationRepository;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationGetDTO;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationInsertDTO;
import com.wp.domain.student.dto.StudentGetDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public LectureEvaluationGetDTO findByLno(long lno) {
        LectureEvaluation entity = lectureEvaluationRepository.findByLno(lno);
        if(entity==null){
            return null;
        }
        return new LectureEvaluationGetDTO(entity);
    }

    public Page<LectureEvaluation> findLectureEvaluations(Pageable pageable) {
        return lectureEvaluationRepository.findAll(PageRequest.of(pageable.getPageNumber(), 10,
                new Sort(Sort.Direction.DESC, "lno")));
    }
    @Transactional
    public boolean deleteLectureEvaluation(long lno) {
        LectureEvaluation data= lectureEvaluationRepository.findByLno(lno);
        data.setCheck_delete("T");
        return true;
    }

    public List<LectureEvaluation> getLectureEvaluationList() {
            return lectureEvaluationRepository.findByList();
    }
    
    public Page<LectureEvaluation> searchLectureEvaluation(Pageable pageable, String text, String date, String option) {
        String addQuery = null;
        String selectQuery = null;
        String orderQuery = null;
        int dateNum = 0;

        if (option.equals("title")) {
            addQuery = "lecturename like ?1";
            selectQuery = "";
            orderQuery = "order by register_date desc";
            text = "%" + text + "%";
        }
        else if (option.equals("professor")) {
            addQuery = "professor like ?1";
            selectQuery = "";
            orderQuery = "order by register_date desc";
            text = "%" + text + "%";
        }
        else if (option.equals("content")) {
            addQuery = "match(content) against(?1 in boolean mode)";
            selectQuery = ", match(content) against(?1 in boolean mode) as score";
            orderQuery = "order by score desc, register date desc";
        }
        else if (option.equals("writer")) {
            addQuery = "nickname like ?1";
            selectQuery = "";
            orderQuery = "order by register_date desc";
            text = "%" + text + "%";
        }

        if (date.equals("all")) {
            return lectureEvaluationRepository.searchLectureEvaluationOptions(addQuery, selectQuery, orderQuery, text, PageRequest.of(pageable.getPageNumber(), 10));
        } 
        else {
            if (date.equals("1week"))
                dateNum = 7;
            else if (date.equals("1month"))
                dateNum = 30;
            else if (date.equals("6month"))
                dateNum = 180;
        }
        return lectureEvaluationRepository.searchLectureEvaluationOptionsAndDate(addQuery, selectQuery, orderQuery, text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
    }
}
