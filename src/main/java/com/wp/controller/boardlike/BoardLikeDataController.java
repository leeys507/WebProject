package com.wp.controller.boardlike;

import com.wp.domain.boardlike.dto.BoardLikeInsertDTO;
import com.wp.service.boardlike.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardLikeDataController {
    private final BoardLikeService boardLikeService;
    @PostMapping("/boardLike/insertBoardLike")
    public boolean insertBoardLike(@ModelAttribute BoardLikeInsertDTO data) {
        return boardLikeService.insertBoardLike(data);
    }
    @PutMapping("/boardLike/updateBoardLike")
    public int updateBoardLike(String sid, long bno) {
        return boardLikeService.updateBoardLike(sid,bno);
    }
}
