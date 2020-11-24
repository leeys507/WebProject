package com.wp.service.studentdrop;

import com.wp.domain.studentdrop.StudentDrop;
import com.wp.domain.studentdrop.StudentDropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudentDropServiceImpl implements StudentDropService {
    private final StudentDropRepository studentDropRepository;

    @Transactional
    public boolean dropStudentInsert(String sid) {
        StudentDrop entity=new StudentDrop();
        entity.setSid(sid);
        return studentDropRepository.save(entity)!=null;
    }
}
