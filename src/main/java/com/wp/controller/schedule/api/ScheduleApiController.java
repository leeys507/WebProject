package com.wp.controller.schedule.api;

import com.wp.domain.schedule.Schedule;
import com.wp.domain.schedule.dto.ResponseDto;
import com.wp.service.schedule.ScheduleService;
import com.wp.service.schedule.SeleniumService;
import com.wp.domain.schedule.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScheduleApiController {

    @Autowired
    private ScheduleService scheduleService;


    Schedule schedule;

    @GetMapping(value = "/schedule/save")
    public ResponseDto<Integer> save(HttpServletRequest request, Model model) throws Exception {

        HttpSession session = request.getSession(true);


        String title = request.getParameter("title");
        String part = request.getParameter("part");
        String profname = request.getParameter("profname");
        String placename = request.getParameter("placename");
        String colornum = request.getParameter("colornum");
        String topnum = request.getParameter("topnum");
        String heightnum = request.getParameter("heightnum");
        String coursenum = request.getParameter("coursenum");
        String dayid = request.getParameter("dayid");



        schedule=new Schedule();

            schedule.setTitle(title);
            schedule.setPart(part);
            schedule.setProfname(profname);
            schedule.setPlacename(placename);
            schedule.setColornum(colornum);
            schedule.setTopnum(topnum);
            schedule.setHeightnum(heightnum);
            schedule.setCoursenum(coursenum);
            schedule.setDayid(dayid);
            schedule.setSid((String)session.getAttribute("id"));
            scheduleService.저장(schedule);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @Autowired
    private ScheduleRepository ScheduleRepository;

    @GetMapping(value = "/schedule/delete")
    public String delete(HttpServletRequest request) throws Exception {

        String sid=request.getParameter("sid");

        ScheduleRepository.deleteBySid(sid);
        return "ok";
    }

    @GetMapping(value = "/schedule/deleteload")
    public String deleteload(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession(true);
        return (String)session.getAttribute("id");
    }


    @GetMapping(value = "/schedule/load")
    public String load(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);

        List<Schedule> schedule=ScheduleRepository.findBySid((String)session.getAttribute("id"));
        String all="";

        for(int i=0;i<schedule.size();i++) {
            Schedule a = schedule.get(i);
            all += a.getTitle()+"*"+ a.getPart() + "*" + a.getProfname()+"*" + a.getPlacename()+"*" + a.getColornum()+"*" + a.getTopnum()+"*" + a.getHeightnum()+"*" + a.getCoursenum()+"*" + a.getDayid() + "!";
        }

        return all;
    }



    @GetMapping(value="/schedule/search")
    public String search(HttpServletRequest request,Model model){

        String con3=request.getParameter("con3");
        String con2=request.getParameter("con2");
        String con1=request.getParameter("con1");

        String searchname=request.getParameter("searchname");
        String searchnum=request.getParameter("searchnum");
        String grade=request.getParameter("grade");



        if(searchname=="")
        {
            searchname=null;
        }
        if(searchnum=="")
        {
            searchnum=null;
        }


        SeleniumService selTest = new SeleniumService();
        String result=selTest.crawl(Integer.parseInt(con3),Integer.parseInt(con2),Integer.parseInt(con1),searchname,searchnum,Integer.parseInt(grade));

        return result;
    }



    @GetMapping(value="/schedule/searchload")
    public String searchload(HttpServletRequest request,Model model){

        String searchname=request.getParameter("searchname");

        SeleniumService selTest = new SeleniumService();
        String result=selTest.crawl(-1,-1,-1,searchname,null,0);

        return result;
    }

}