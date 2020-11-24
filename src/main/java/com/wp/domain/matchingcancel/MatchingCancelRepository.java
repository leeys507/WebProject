package com.wp.domain.matchingcancel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MatchingCancelRepository extends JpaRepository<MatchingCancel, Long>, PagingAndSortingRepository<MatchingCancel, Long> {
    @Query(value = "SELECT EXISTS (SELECT * FROM matchingcancel mc WHERE mc.bno = :bno)", nativeQuery = true)
    int existsByBno(@Param("bno") long bno);

    @Query(value="SELECT * FROM matchingcancel mc WHERE mc.bno = :bno", nativeQuery = true)
    MatchingCancel findByBno(@Param("bno") long bno);


}
