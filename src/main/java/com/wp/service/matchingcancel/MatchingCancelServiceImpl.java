package com.wp.service.matchingcancel;

import com.wp.domain.matchingcancel.MatchingCancelRepository;
import com.wp.domain.matchingcancel.dto.MatchingCancelInsertDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MatchingCancelServiceImpl implements MatchingCancelService {
    private final MatchingCancelRepository matchingCancelRepository;
    @Transactional
    public boolean registerMatchingCancel(MatchingCancelInsertDTO data) {
        if(matchingCancelRepository.existsByBno(data.getBno())==1){
            return false;
        }
        return matchingCancelRepository.save(data.toEntity())!=null;
    }
}
