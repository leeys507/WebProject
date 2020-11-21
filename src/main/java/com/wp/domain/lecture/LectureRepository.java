package com.wp.domain.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query(value = "SELECT * FROM lecture l WHERE l.sid= :sid", nativeQuery = true)
    List<Lecture> findBySid(@Param("sid")String sid);

    @Query(value = "SELECT * FROM lecture l WHERE l.sid= :sid AND l.lecturenum=:ln", nativeQuery = true)
    Lecture findBySidAndLecturenum(@Param("sid")String sid,@Param("ln")int lecturenum);
    @Query(value = "SELECT EXISTS (SELECT * FROM lecture l WHERE l.sid = :sid)", nativeQuery = true)
    int existsBySid(@Param("sid")String sid);

    @Query(value = "SELECT * FROM lecture l WHERE l.sid= :sid AND l.lno=:lno", nativeQuery = true)
    Lecture findBySidAndlno(@Param("sid")String sid, @Param("lno")int lno);
}
