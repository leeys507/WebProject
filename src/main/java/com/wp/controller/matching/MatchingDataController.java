package com.wp.controller.matching;

import com.wp.domain.matching.dto.MatchingInsertDTO;
import com.wp.service.matching.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class MatchingDataController {
    private final MatchingService matchingService;
    @PostMapping("/matchingInsert")
    public String InsertBoard(@ModelAttribute MatchingInsertDTO data) throws Exception {
        return matchingService.InsertMatching(data);
    }
    @PutMapping("/matchingProceed")
    public boolean ProceedBoard(long bno, String sid) throws Exception{
        return matchingService.ProceedMatching(bno, sid);
    }
    @PutMapping("/matchingCancel")
    public boolean CancelBoard(long bno) throws Exception{
        return matchingService.CancelMatching(bno);
    }
    @PutMapping("/matchingSuccess")
    public boolean SuccessBoard(long bno) throws Exception{
        return true;
    }
}
