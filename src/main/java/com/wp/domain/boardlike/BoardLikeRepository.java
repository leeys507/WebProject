package com.wp.domain.boardlike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    @Query(value = "SELECT bl.check_like FROM boardlike bl WHERE bl.bno = :bno and bl.sid = :sid", nativeQuery = true)
    String findCheckBySidAndBno(@Param("sid")String sid, @Param("bno") long bno);

    @Query(value = "SELECT * FROM boardlike bl WHERE bl.bno = :bno and bl.sid = :sid", nativeQuery = true)
    BoardLike findBySidAndBno(@Param("sid")String sid,@Param("bno") long bno);
}

