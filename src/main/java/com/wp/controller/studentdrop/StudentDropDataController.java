package com.wp.controller.studentdrop;

import com.wp.domain.studentdrop.StudentDrop;
import com.wp.service.studentdrop.StudentDropService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StudentDropDataController {
    private final StudentDropService studentDropService;
    @PostMapping("/yu/dropStudent")
    public boolean dropStudentInsert(String sid){
        return studentDropService.dropStudentInsert(sid);
    }
}
