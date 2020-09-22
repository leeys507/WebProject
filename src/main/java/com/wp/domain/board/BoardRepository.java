package com.wp.domain.board;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("select p from board p ORDER BY p.bno DESC ")
    List<Board> findAllDesc();
}