package com.wp.controller.boardcomment;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wp.domain.boardcomment.dto.BoardCommentGetDTO;
import com.wp.domain.boardcomment.dto.BoardCommentInsertDTO;
import com.wp.service.boardcomment.BoardCommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardCommentDataController {
    private final BoardCommentService boardCommentService;

    @PostMapping("/yu/boardComment/insertBoardComment")    // view
    public boolean registerBoardComment(@ModelAttribute BoardCommentInsertDTO data) {
    	if(data.getCcno() != 0) {
    		BoardCommentGetDTO commentInfo = boardCommentService.findByCno(data.getCcno());
    		if(commentInfo == null) return false;
    		data.setGroup_id(commentInfo.getGroup_id());
    	}
    	else data.setGroup_id(boardCommentService.nextGroupID());
    	return boardCommentService.insertBoardComment(data);
    }
    
	@PostMapping("/yu/boardComment/getBoardCommentInfo")
	public BoardCommentGetDTO getBoardComment(@RequestBody long cno) {
		BoardCommentGetDTO data = boardCommentService.findByCno(cno);
		if (data == null) {
			// TODO => 검색 실패 메시지 전달
			return null;
		}
		return data;
	}
	
	@PutMapping("/yu/boardComment/updateBoardComment")
	public boolean updateBoardComment(long bno, long cno, String content) {
		return boardCommentService.updateBoardComment(bno, cno, content);
	}
	
	@PutMapping("/yu/boardComment/deleteBoardComment")		// change check_delete, delete_date
	public boolean deleteBoardComment(long cno) {
		return boardCommentService.deleteBoardComment(cno);
	}
}