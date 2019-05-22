package com.cms.controller;

import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.base.query.PageQuery;
import com.cms.entity.ClassroomInfo;
import com.cms.entity.Course;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.*;
import com.cms.util.DateUtil;
import com.cms.util.Result;
import com.cms.vo.CourseVO;
import com.cms.vo.StudentVO;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2019.5.18.
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseCrudController<Course>{

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseInfoService courseInfoService;

    @Autowired
    private ClassroomInfoService classroomInfoService;

    @Autowired
    private ClassInfoService classInfoService;

    @Autowired
    private UserService userService;

    @GetMapping
    @RequiresPermissions("course:view")
    public String rolePage(Model model) {

        model.addAttribute("courseList", courseService.queryAll());
        model.addAttribute("userList",userService.queryList(new User().setRoleIds("3")));
        model.addAttribute("courseinfoList",courseInfoService.queryAll());
        model.addAttribute("classroomList",classroomInfoService.queryAll());
        model.addAttribute("classList",classInfoService.queryAll());
        return "system/course";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("course:view")
    @Override
    public Result<List<CourseVO>> queryList(Course course, PageQuery pageQuery) {
        Page<Course> page = (Page<Course>) courseService.queryList(course, pageQuery);
        List<CourseVO> courseVOS = new ArrayList<>();
        page.forEach(s -> {
            CourseVO courseVO = new CourseVO(s);
            courseVO.setCoursename(courseInfoService.queryById(s.getCourseInfoId()).getName());
            courseVO.setTeachername(userService.queryById(s.getTeacherId()).getUsername());
            courseVO.setClassname(classInfoService.queryById(s.getClassId()).getClassShortname());
            ClassroomInfo classroomInfo = classroomInfoService.queryById(s.getClassroomId());
            courseVO.setClassroomname(classroomInfo.getFloorNo() + "号楼" + classroomInfo.getRoomNo());
            courseVOS.add(courseVO);
        });
        return Result.success(courseVOS).addExtra("total", page.getTotal());
    }

    @ResponseBody
    @RequiresPermissions("student:create")
    @SystemLog("新建课程表信息")
    @PostMapping("/create")
    @Override
    public Result create(@Validated Course course) {
        courseService.create(course);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("course:update")
    @SystemLog("课程表管理更新角色")
    @PostMapping("/update")
    @Override
    public Result update(@Validated Course course) {
        courseService.updateNotNull(course);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("course:delete")
    @SystemLog("课程表管理删除角色")
    @PostMapping("/delete-batch")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id") Object[] ids) {
        super.deleteBatchByIds(ids);
        return Result.success();
    }

}
