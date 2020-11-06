package com.wp.controller.boardcomment;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.boardcomment.dto.BoardCommentInsertDTO;
import com.wp.service.boardcomment.BoardCommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardCommentDataController {
    private final BoardCommentService boardCommentService;

    @PostMapping("/boardComment/boardCommentInsertTest")    // view
    public boolean registerBoardComment(@ModelAttribute BoardCommentInsertDTO data) {
    	data.setGroup_id(boardCommentService.nextGroupID());
    	data.setCcno(0);
    	return boardCommentService.registerBoardComment(data);
    }
}