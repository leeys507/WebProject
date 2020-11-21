package com.wp.controller.lecture;

import com.wp.service.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LectureDataController {
    private final LectureService lectureService;
    @PostMapping("/yu/lecture/insertLecture")    // view
    public boolean registerLectureEvaluation() throws InterruptedException {
        return lectureService.insertLecture();
    }
}
