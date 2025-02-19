package com.wp.domain.matching;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.transaction.Transactional;

public interface MatchingRepository extends JpaRepository<Matching, Long>, PagingAndSortingRepository<Matching, Long>, MatchingCustomRepository {
	@Query(value = "SELECT * FROM matching m WHERE m.boardtype=:boardtype AND m.check_delete='F'", nativeQuery = true)
	Page<Matching> findAllByBoardtype(@Param("boardtype") String boardtype, Pageable pageable);
	
	@Query(value = "SELECT * FROM matching m WHERE m.bno = :bno", nativeQuery = true)
	Matching findByBno(@Param("bno") long bno);

	@Transactional
	@Modifying
	@Query(value = "UPDATE matching SET read_count = :read_count WHERE bno = :bno", nativeQuery = true)
	int updateReadCount(@Param("bno") long bno, @Param("read_count") int read_count);

	@Query(value = "SELECT * FROM matching m WHERE m.check_delete='F' and register_date > date_sub(now(), interval 7 day) ORDER BY m.register_date DESC limit 6", nativeQuery = true)
    List<Matching> findRecentlyMatchingList();
	
    @Query(value = "select * from matching where match(title, content) against(:text in boolean mode) and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Matching> searchMatchingAll(@Param("text")String text, Pageable pageable);
    
    @Query(value = "select * from matching where match(title, content) against(:text in boolean mode) "
    		+ "and register_date >= DATE_SUB(NOW(), INTERVAL :date DAY) and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Matching> searchMatchingDates(@Param("text")String text, @Param("date")int date, Pageable pageable);
    
    @Query(value = "select * from matching where match(title, content) against(:text in boolean mode)"
    		+ " and boardtype = :boardtype and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Matching> searchMatchingType(@Param("boardtype")String boardtype, @Param("text")String text, Pageable pageable);
    
    @Query(value = "select * from matching where match(title, content) against(:text in boolean mode) "
    		+ "and boardtype = :boardtype and register_date >= DATE_SUB(NOW(), INTERVAL :date DAY) and check_delete = 'F' "
    		+ "order by match(title, content) against(:text in boolean mode) desc, register_date desc", nativeQuery = true)
    Page<Matching> searchMatchingTypeDates(@Param("boardtype")String boardtype, @Param("text")String text, @Param("date")int date, Pageable pageable);
    
	@Query(value = "SELECT MAX(m.bno) FROM matching m", nativeQuery = true)
	Long MaxBno();
	@Query(value = "SELECT * FROM matching m WHERE m.check_delete='T'", nativeQuery = true)
    Page<Matching> findAllByDelete(Pageable pageable);
}
