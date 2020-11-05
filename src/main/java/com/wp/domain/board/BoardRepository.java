package com.wp.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, PagingAndSortingRepository<Board, Long> {
    @Query("select p from board p ORDER BY p.bno DESC ")
    List<Board> findAllDesc();

    @Query(value = "SELECT * FROM board b WHERE b.boardtype=:boardtype AND b.check_delete='F' ", nativeQuery = true)
    Page<Board> findAllByBoardtype(@Param("boardtype")String boardtype, Pageable pageable);
    @Query(value = "SELECT * FROM board b WHERE b.bno = :bno AND b.check_delete='F'", nativeQuery = true)
    Board findByBno(@Param("bno") long bno);
}