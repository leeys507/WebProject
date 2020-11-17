package com.wp.domain.matching;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MatchingRepository extends JpaRepository<Matching, Long>, PagingAndSortingRepository<Matching, Long>, MatchingCustomRepository {
	@Query(value = "SELECT * FROM matching m WHERE m.boardtype=:boardtype AND m.check_delete='F'", nativeQuery = true)
	Page<Matching> findAllByBoardtype(@Param("boardtype") String boardtype, Pageable pageable);
	
	@Query(value = "SELECT * FROM matching m WHERE m.bno = :bno ", nativeQuery = true)
	Matching findByBno(@Param("bno") long bno);
	
    @Query(value = "select * from matching where match(title, content) against(:text in boolean mode)"
    		+ " and boardtype = :boardtype", nativeQuery = true)
    Page<Matching> searchBoardType(@Param("boardtype")String boardtype, @Param("text")String text, Pageable pageable);
    
    @Query(value = "select * from matching where match(title, content) against(:text in boolean mode) "
    		+ "and boardtype = :boardtype and register_date >= DATE_SUB(NOW(), INTERVAL :date DAY)", nativeQuery = true)
    Page<Matching> searchBoardTypeDates(@Param("boardtype")String boardtype, @Param("text")String text, @Param("date")int date, Pageable pageable);
	
	@Query(value = "SELECT MAX(m.bno) FROM matching m", nativeQuery = true)
	Long MaxBno();
}
