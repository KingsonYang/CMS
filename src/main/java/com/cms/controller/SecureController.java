package com.cms.controller;

import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.base.query.PageQuery;
import com.cms.entity.*;
import com.cms.service.*;
import com.cms.util.Result;
import com.cms.vo.CourseVO;
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

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    private ClassInfoService classInfoService;

    @Autowired
    private ClassroomInfoService classroomInfoService;

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
    @RequiresPermissions("userinfo:*")
    @RequestMapping("/userinfo")
    public String userinfoPage(Model model) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.queryOne(new User().setUsername(username));
        model.addAttribute("user",user);
        ClassInfo classInfo = null;
        Student student = studentService.queryOne(new Student().setStuId(user.getId()));
        if(student == null){
            classInfo = new ClassInfo(100,"xx","xx","xx","xx");
            model.addAttribute("classInfo",classInfo);
            return "system/userinfo";
        }
        classInfo = classInfoService.queryById(student.getClassId());
        model.addAttribute("classInfo",classInfo);
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


    @GetMapping
    @RequiresPermissions("chooseClass:*")
    @RequestMapping("/chooseClass")
    public String chooseClassPage(Model model) {

        model.addAttribute("courseList", courseService.queryAll());
        model.addAttribute("userList",userService.queryList(new User().setRoleIds("3")));
        model.addAttribute("courseinfoList",courseInfoService.queryList(new CourseInfo().setAct(2)));
        model.addAttribute("classroomList",classroomInfoService.queryAll());
        model.addAttribute("classList",classInfoService.queryAll());
        return "system/chooseClass";
    }

    @ResponseBody
    @GetMapping("/chooseClass/list")
    @RequiresPermissions("chooseClass:*")
    public Result<List<CourseVO>> queryClassList(Course course, PageQuery pageQuery) {
        Page<Course> page = (Page<Course>) courseService.queryList(course, pageQuery);
        List<CourseVO> courseVOS = new ArrayList<>();
        page.forEach(s -> {
            CourseVO courseVO = new CourseVO(s);

            CourseInfo courseInfo = courseInfoService.queryOne(new CourseInfo().setId(s.getCourseInfoId()));
            if (courseInfo.getAct() == 2){
                courseVO.setCoursename(courseInfoService.queryById(s.getCourseInfoId()).getName());
                courseVO.setTeachername(userService.queryById(s.getTeacherId()).getUsername());
                courseVO.setClassname(classInfoService.queryById(s.getClassId()).getClassShortname());
                ClassroomInfo classroomInfo = classroomInfoService.queryById(s.getClassroomId());
                courseVO.setClassroomname(classroomInfo.getFloorNo() + "号楼" + classroomInfo.getRoomNo());
                courseVOS.add(courseVO);
            }

        });
        return Result.success(courseVOS).addExtra("total", page.getTotal());
    }


    @ResponseBody
    @RequiresPermissions("chooseClass:*")
    @SystemLog("选择课程")
    @PostMapping("/chooseOK")
    public Result chooseClassOK(@NotNull @RequestParam("id") Object[] ids) {

        for (Object id : ids){
            String i = (String) id;
            Course course = courseService.queryOne(new Course().setId(Integer.valueOf(i)));
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryOne(new User().setUsername(username));
            Student student = studentService.queryOne(new Student().setStuId(user.getId()));
            if (student.getClassId() == course.getClassId()){
                return Result.success();
            }
        }

        return Result.success();
    }


}
