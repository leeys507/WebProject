package com.wp.controller.matching;

import com.wp.domain.matching.dto.MatchingInsertDTO;
import com.wp.service.matching.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MatchingDataController {
    private final MatchingService matchingService;
    @PostMapping("/matchingInsert")
    public String InsertBoard(@ModelAttribute MatchingInsertDTO data) throws Exception {
        return matchingService.InsertMatching(data);
    }

}
