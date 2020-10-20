package com.wp.domain.boardcomment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.wp.domain.board.Board;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long>, PagingAndSortingRepository<BoardComment, Long> {
	@Query(value = "SELECT * FROM boardcomment bc WHERE bc.cno = :cno", nativeQuery = true)
	BoardComment findByCno(@Param("cno") long cno);
    
	@Query(value = "SELECT * FROM boardcomment bc WHERE bc.bno = :bno order by group_id, cno", nativeQuery = true)
    Page<BoardComment> findAllByBoardForeignkey(@Param("bno")Board bno, Pageable pageable);
	
	@Query(value = "SELECT MAX(group_id) FROM boardcomment", nativeQuery = true)
	int findMaxGroupID();
	
	@Query(value = "SELECT count(*) FROM boardcomment", nativeQuery = true)
	int getBoardCommentCount();
}