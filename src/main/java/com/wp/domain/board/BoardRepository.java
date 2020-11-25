package com.wp.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Board, Long>, PagingAndSortingRepository<Board, Long>, BoardCustomRepository {
    @Query("select p from board p ORDER BY p.bno DESC ")
    List<Board> findAllDesc();

    @Query(value = "SELECT * FROM board WHERE boardtype = :boardtype AND check_delete = 'F'", nativeQuery = true)
    Page<Board> findAllByBoardtype(@Param("boardtype")String boardtype, Pageable pageable);
    
    @Query(value = "SELECT * FROM board WHERE bno = :bno AND check_delete = 'F'", nativeQuery = true)
    Board findByBno(@Param("bno") long bno);
    
	@Transactional
	@Modifying
	@Query(value = "UPDATE board SET read_count = :read_count WHERE bno = :bno", nativeQuery = true)
	int updateReadCount(@Param("bno") long bno, @Param("read_count") int read_count);
	
	@Query(value = "select * from board where register_date > date_sub(now(), interval 7 day) and check_delete = 'F' and like_count >= 5 order by like_count limit 6", nativeQuery = true)
	List<Board> getRecentlyPopularBoard();
    
    @Query(value = "select * from board where match(title, content) against(:text in boolean mode) and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Board> searchBoardAll(@Param("text")String text, Pageable pageable);
    
    @Query(value = "select * from board where match(title, content) against(:text in boolean mode) "
    		+ "and boardtype = :boardtype and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Board> searchBoardType(@Param("boardtype")String boardtype, @Param("text")String text, Pageable pageable);
    
    @Query(value = "select * from board where match(title, content) against(:text in boolean mode) "
    		+ "and register_date >= DATE_SUB(NOW(), INTERVAL :date DAY) and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Board> searchBoardDates(@Param("text")String text, @Param("date")int date, Pageable pageable);
    
    @Query(value = "select * from board where match(title, content) against(:text in boolean mode) "
    		+ "and boardtype = :boardtype and register_date >= DATE_SUB(NOW(), INTERVAL :date DAY) and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Board> searchBoardTypeDates(@Param("boardtype")String boardtype, @Param("text")String text, @Param("date")int date, Pageable pageable);
}