package com.cms.controller;

import com.cms.base.controller.BaseCrudController;
import com.cms.base.query.PageQuery;
import com.cms.entity.Course;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.*;
import com.cms.util.Result;
import com.cms.vo.ScheduleVO;
import com.github.pagehelper.Page;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2019.5.18.
 */
@Controller
@RequestMapping("/")
public class SecureController extends BaseCrudController<User> {


    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseInfoService courseInfoService;

    ScheduleVO scheduleVO1 = new ScheduleVO();
    ScheduleVO scheduleVO2 = new ScheduleVO();
    ScheduleVO scheduleVO3 = new ScheduleVO();
    ScheduleVO scheduleVO4 = new ScheduleVO();
    ScheduleVO scheduleVO5 = new ScheduleVO();

    @GetMapping
    @RequiresPermissions("updatePwd:*")
    @RequestMapping("/updatePwd")
    public String changePwdPage() {

        return "system/changePwd";
    }

    @GetMapping
    @RequiresPermissions("*:*")
    @RequestMapping("/userinfo")
    public String userinfoPage() {

        return "system/userinfo";
    }


    @GetMapping
    @RequiresPermissions("schedule:*")
    @RequestMapping("/schedule")
    public String schedulePage() {

        return "system/schedule";
    }

    @ResponseBody
    @GetMapping("/schedule/list")
    @RequiresPermissions("schedule:*")
    public Result<List<ScheduleVO>> queryList(Course course, PageQuery pageQuery) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.queryOne(new User().setUsername(username));
        int stuId = user.getId();
        Student student = studentService.queryOne(new Student().setStuId(stuId));
        Page<Course> page = (Page<Course>) courseService.queryList(course.setClassId(student.getClassId()), pageQuery);
        List<ScheduleVO> scheduleVOS = new ArrayList<>();

        page.forEach(u -> {
            String tt = u.getTeachTime().toString();
            if(tt.endsWith("1")) setOne(u,tt);
            if(tt.endsWith("2")) setTwo(u,tt);
            if(tt.endsWith("3")) setThree(u,tt);
            if(tt.endsWith("4")) setFour(u,tt);
        });
        setLeft();
        scheduleVOS.add(scheduleVO1);
        scheduleVOS.add(scheduleVO2);
        scheduleVOS.add(scheduleVO3);
        scheduleVOS.add(scheduleVO4);
        scheduleVOS.add(scheduleVO5);
        return Result.success(scheduleVOS).addExtra("total", page.getTotal());
    }

    public void setLeft(){
        scheduleVO1.setTt("上午一二节课");
        scheduleVO2.setTt("上午三四节课");
        scheduleVO3.setTt("下午一二节课");
        scheduleVO4.setTt("下午三四节课");
        scheduleVO5.setTt("晚自习");

    }
    public void setOne(Course course, String tt){
        setSch(scheduleVO1,course,tt);
    }
    public void setTwo(Course course, String tt){
        setSch(scheduleVO2,course,tt);

    }
    public void setThree(Course course, String tt){
        setSch(scheduleVO3,course,tt);
    }
    public void setFour(Course course, String tt){
        setSch(scheduleVO4,course,tt);
    }


    public void setSch(ScheduleVO scheduleVO,Course course, String tt){
        if (tt.startsWith("1")) {
            scheduleVO.setMon(courseInfoService.queryById(course.getCourseInfoId()).getName());
        }
        if (tt.startsWith("2")) {
            scheduleVO.setTues(courseInfoService.queryById(course.getCourseInfoId()).getName());
        }
        if (tt.startsWith("3")) {
            scheduleVO.setWes(courseInfoService.queryById(course.getCourseInfoId()).getName());
        }
        if (tt.startsWith("4")) {
            scheduleVO.setThur(courseInfoService.queryById(course.getCourseInfoId()).getName());
        }
        if (tt.startsWith("5")) {
            scheduleVO.setFri(courseInfoService.queryById(course.getCourseInfoId()).getName());
        }
        if (tt.startsWith("6")) {
            scheduleVO.setThur(courseInfoService.queryById(course.getCourseInfoId()).getName());
        }
        if (tt.startsWith("7")) {
            scheduleVO.setFri(courseInfoService.queryById(course.getCourseInfoId()).getName());
        }

    }

    @ResponseBody
    @GetMapping("/checkOldPwd")
    public void checkOldPwd(String newPassword, Model model) {
        Long userId = (Long) SecurityUtils.getSubject().getSession().getAttribute("currentUserId");
        userService.changePassword(userId, newPassword);
    }

    @ResponseBody
    @PostMapping("/updatePwd/update")
    public Result changePassword(@RequestParam(value = "password", required = true) String newPassword) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String userId = (String) session.getAttribute("currentUserId");
        userService.changePassword(Long.valueOf(userId), newPassword);
        return Result.success();

    }
}
