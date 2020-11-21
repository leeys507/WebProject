package com.wp.domain.lectureevaluation;

import com.wp.domain.lectureevaluation.dto.LectureEvaluationGetDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureEvaluationRepository extends JpaRepository<LectureEvaluation, Long>, PagingAndSortingRepository<LectureEvaluation, Long> {
    @Query(value = "SELECT * FROM lectureevaluation le WHERE le.sid= :sid", nativeQuery = true)
    List<LectureEvaluation> findBySid(@Param("sid")String sid);

    @Query(value = "SELECT EXISTS (SELECT * FROM lectureevaluation le WHERE le.sid = :sid)", nativeQuery = true)
    int existsBySid(@Param("sid") String sid);
    @Query(value = "SELECT * FROM lectureevaluation le WHERE le.sid= :sid AND le.lno= :lno", nativeQuery = true)
    LectureEvaluation findBySidAndlno(@Param("sid")String sid,@Param("lno") int lno);

    @Query(value = "SELECT * FROM lectureevaluation le WHERE le.lno= :lno", nativeQuery = true)
    LectureEvaluation findByLno(@Param("lno") long lno);
}
