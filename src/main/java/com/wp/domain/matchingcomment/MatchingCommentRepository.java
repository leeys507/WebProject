package com.wp.domain.matchingcomment;

import com.wp.domain.matching.Matching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MatchingCommentRepository extends JpaRepository<MatchingComment, Long>, PagingAndSortingRepository<MatchingComment, Long> {
    @Query(value = "SELECT * FROM matchingcomment mc WHERE mc.cno = :cno", nativeQuery = true)
    MatchingComment findByCno(@Param("cno") long cno);

    @Query(value = "SELECT * FROM matchingcomment mc WHERE mc.bno = :bno order by cno", nativeQuery = true)
    Page<MatchingComment> findAllByBoardForeignkey(@Param("bno") Matching bno, Pageable pageable);

    @Query(value = "SELECT * FROM matchingcomment mc WHERE mc.bno = :bno and mc.cno = :cno", nativeQuery = true)
    MatchingComment findByBnoAndCno(@Param("bno") long bno, @Param("cno") long cno);
}
