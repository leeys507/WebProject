package com.wp.service.schedule;

import com.wp.domain.schedule.Schedule;
import com.wp.domain.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository ScheduleRepository;


    @Transactional
    public void 저장(Schedule sd) {

        ScheduleRepository.save(sd);
    }

    @Transactional
    public void 삭제() {

        ScheduleRepository.deleteAll();
    }



    @Transactional
    public List<Schedule> getSchedule(){

        List<Schedule> schedule=ScheduleRepository.findAll();
        return schedule;
    }
}