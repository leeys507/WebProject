package com.wp.domain.lectureevaluation;

import com.wp.domain.matching.Matching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureEvaluationRepository extends JpaRepository<LectureEvaluation, Long>, PagingAndSortingRepository<LectureEvaluation, Long>, LectureEvaluationCustomRepository {
    @Query(value = "SELECT * FROM lectureevaluation le WHERE le.sid= :sid AND le.check_delete='F'", nativeQuery = true)
    List<LectureEvaluation> findBySid(@Param("sid")String sid);

    @Query(value = "SELECT * FROM lectureevaluation le WHERE le.lno= :lno", nativeQuery = true)
    LectureEvaluation findByLno(@Param("lno") long lno);

    @Query(value = "SELECT EXISTS (SELECT * FROM lectureevaluation le WHERE le.sid = :sid AND le.lecturenum=:ln)", nativeQuery = true)
    int existsBySidAndLecturenum(@Param("sid") String sid,@Param("ln") int lecturenum);

    @Query(value = "SELECT * FROM lectureevaluation le WHERE le.check_delete='F' ORDER BY le.register_date DESC limit 10", nativeQuery = true)
    List<LectureEvaluation> findByList();

    @Query(value = "SELECT * FROM lectureevaluation le WHERE le.check_delete='F'", nativeQuery = true)
    Page<LectureEvaluation> findAll(Pageable pageable);
}
