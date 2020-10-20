package com.wp.domain.board;

import com.wp.domain.boardcomment.BoardComment;
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

    Page<Board> findAllByBoardtype(String boardtype, Pageable pageable);
}