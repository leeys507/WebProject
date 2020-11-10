package com.wp.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, PagingAndSortingRepository<Board, Long>, BoardCustomRepository {
    @Query("select p from board p ORDER BY p.bno DESC ")
    List<Board> findAllDesc();

    @Query(value = "SELECT * FROM board b WHERE b.boardtype=:boardtype AND b.check_delete='F'", nativeQuery = true)
    Page<Board> findAllByBoardtype(@Param("boardtype")String boardtype, Pageable pageable);
    
    @Query(value = "SELECT * FROM board b WHERE b.bno = :bno AND b.check_delete='F'", nativeQuery = true)
    Board findByBno(@Param("bno") long bno);
    
    @Query(value = "select * from board where match(title, content) against(:text in boolean mode)", nativeQuery = true)
    Page<Board> searchBoardAll(@Param("text")String text, Pageable pageable);
    
//    @Query(value = "select * from board where :addQuery", nativeQuery = true)
//    Page<Board> searchBoardOptions(@Param("addQuery")String addQuery, Pageable pageable);
    
    @Query(value = "select * from board where match(title, content) against(:text in boolean mode) "
    		+ "and register_date >= DATE_SUB(NOW(), INTERVAL :date DAY)", nativeQuery = true)
    Page<Board> searchBoardDates(@Param("text")String text, @Param("date")int date, Pageable pageable);
    
//    @Query(value = "select * from board where :addQuery and register_date >= DATE_SUB(NOW(), INTERVAL :date DAY)", nativeQuery = true)
//    Page<Board> searchBoardOptionsAndDate(@Param("addQuery")String addQuery, @Param("date")String date, Pageable pageable);
}