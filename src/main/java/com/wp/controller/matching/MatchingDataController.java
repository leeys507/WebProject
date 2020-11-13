package com.wp.controller.matching;

import com.wp.domain.board.dto.BoardUpdateDTO;
import com.wp.domain.matching.dto.MatchingInsertDTO;
import com.wp.domain.matching.dto.MatchingUpdateDTO;
import com.wp.service.matching.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class MatchingDataController {
    private final MatchingService matchingService;
    @PostMapping("/yu/matchingInsert")
    public String InsertBoard(@ModelAttribute MatchingInsertDTO data) throws Exception {
        return matchingService.InsertMatching(data);
    }
    @PutMapping("/yu/matchingProceed")
    public boolean ProceedBoard(long bno, String sid, String account) throws Exception{
        return matchingService.ProceedMatching(bno, sid,account);
    }
    @PutMapping("/yu/matchingCancel")
    public boolean CancelBoard(long bno) throws Exception{
        return matchingService.CancelMatching(bno);
    }
    @PutMapping("/yu/matchingSuccess")
    public boolean SuccessBoard(long bno) throws Exception{
        return matchingService.SuccessMatching(bno);
    }
    @PutMapping("/yu/matchingDelete")
    public boolean DeleteBoard(long bno) throws Exception{
        return matchingService.DeleteMatching(bno);
    }
    @PutMapping("/yu/matchingUpdate/{Bno}")
    public long updateBoard(@PathVariable long Bno, MatchingUpdateDTO dto) throws Exception{
        return matchingService.UpdateMatching(Bno, dto);
    }

}
