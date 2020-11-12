package com.wp.domain.matching;

import com.wp.domain.boardcomment.BoardComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MatchingRepository extends JpaRepository<Matching, Long>, PagingAndSortingRepository<Matching, Long> {
	Page<Matching> findAllByBoardtype(String boardtype, Pageable pageable);
	@Query(value = "SELECT * FROM matching m WHERE m.bno = :bno", nativeQuery = true)
	Matching findByBno(@Param("bno") long bno);
}
