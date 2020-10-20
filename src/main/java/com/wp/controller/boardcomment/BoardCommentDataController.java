package com.wp.controller.boardcomment;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.boardcomment.BoardComment;
import com.wp.domain.boardcomment.dto.BoardCommentGetDTO;
import com.wp.domain.boardcomment.dto.BoardCommentInsertDTO;
import com.wp.service.boardcomment.BoardCommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardCommentDataController {
    private final BoardCommentService boardCommentService;

    @PostMapping("/boardComment/insertBoardComment")    // view
    public boolean registerBoardComment(@ModelAttribute BoardCommentInsertDTO data) {
    	if(data.getCcno() != 0) {
    		BoardCommentGetDTO commentInfo = boardCommentService.findByCno(data.getCcno());
    		if(commentInfo == null) return false;
    		data.setGroup_id(commentInfo.getGroup_id());
    	}
    	else data.setGroup_id(boardCommentService.nextGroupID());
    	return boardCommentService.registerBoardComment(data);
    }
    
	@PostMapping("/boardComment/getBoardCommentInfo")
	public BoardCommentGetDTO getStudentInfo(@RequestBody long cno) {
		BoardCommentGetDTO data = boardCommentService.findByCno(cno);
		if (data == null) {
			// TODO => 검색 실패 메시지 전달
			return null;
		}
		return data;
	}
	
	@PutMapping("/boardComment/updateBoardComment")
	public boolean updateBoardComment(long cno) {
		BoardCommentGetDTO data = boardCommentService.findByCno(cno);
		return true;
	}
	
	@PutMapping("/boardComment/deleteBoardComment")		// change check_delete, delete_date
	public boolean deleteBoardComment(long cno) {
		return boardCommentService.deleteBoardComment(cno);
	}
}