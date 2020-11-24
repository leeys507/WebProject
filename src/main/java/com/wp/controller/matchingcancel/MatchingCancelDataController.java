package com.wp.controller.matchingcancel;

import com.wp.domain.matchingcancel.dto.MatchingCancelInsertDTO;
import com.wp.service.matchingcancel.MatchingCancelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MatchingCancelDataController {
    private final MatchingCancelService matchingCancelService;
    @PostMapping("/yu/matchingCancel/insertMatchingCancel")    // view
    public boolean registerMatchingCancel(@ModelAttribute MatchingCancelInsertDTO data) {
        return matchingCancelService.registerMatchingCancel(data);
    }
    @DeleteMapping("/yu/matchingCancelDelete")
    public boolean deleteMatchingCancel(long bno){
        return matchingCancelService.deleteMatchingCancel(bno);
    }
}
