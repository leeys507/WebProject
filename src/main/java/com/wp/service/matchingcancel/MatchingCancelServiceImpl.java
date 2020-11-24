package com.wp.service.matchingcancel;

import com.wp.domain.matchingcancel.MatchingCancel;
import com.wp.domain.matchingcancel.MatchingCancelRepository;
import com.wp.domain.matchingcancel.dto.MatchingCancelInsertDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public boolean findByBno(long bno) {
        if(matchingCancelRepository.existsByBno(bno)==1){
            return false;
        }
        return true;
    }

    public Page<MatchingCancel> findAllMatchingCancel(Pageable pageable) {
        return matchingCancelRepository.findAll(PageRequest.of(pageable.getPageNumber(), 10,
                new Sort(Sort.Direction.DESC, "cno")));
    }

    public boolean deleteMatchingCancel(long bno) {
        MatchingCancel entity = matchingCancelRepository.findByBno(bno);
        matchingCancelRepository.delete(entity);
        return true;
    }
}
