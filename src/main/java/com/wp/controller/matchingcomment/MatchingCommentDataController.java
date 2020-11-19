package com.wp.controller.matchingcomment;

import com.wp.domain.matchingcomment.dto.MatchingCommentInsertDTO;
import com.wp.service.matchingcomment.MatchingCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MatchingCommentDataController {
        private final MatchingCommentService matchingCommentService;
    @PostMapping("/yu/matchingComment/insertMatchingComment")    // view
    public boolean registerMatchingComment(@ModelAttribute MatchingCommentInsertDTO data) {
        return matchingCommentService.registerMatchingComment(data);
    }
    @PutMapping("/yu/matchingComment/updateMatchingComment")
    public boolean updateMatchingComment(long bno, long cno, String content) {
        return matchingCommentService.updateMatchingComment(bno, cno, content);
    }
    @PutMapping("/yu/matchingComment/deleteMatchingComment")		// change check_delete, delete_date
    public boolean deleteMatchingComment(long cno) {
        return matchingCommentService.deleteMatchingComment(cno);
    }
}
