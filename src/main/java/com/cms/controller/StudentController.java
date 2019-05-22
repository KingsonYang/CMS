package com.cms.controller;

import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.base.query.PageQuery;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.ClassInfoService;
import com.cms.service.StudentService;
import com.cms.service.UserService;
import com.cms.util.Result;
import com.cms.vo.StudentVO;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2019.5.18.
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseCrudController<Student>{

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassInfoService classInfoService;

    @Autowired
    private UserService userService;

    @GetMapping
    @RequiresPermissions("student:view")
    public String rolePage(Model model) {

        List<StudentVO> studentVOS = studentService.queryList(studentService.queryAll());
        model.addAttribute("studentList", studentVOS);
        model.addAttribute("classList",classInfoService.queryAll());
        model.addAttribute("userList",userService.queryList(new User().setRoleIds("4")));
        return "system/student";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("student:view")
    @Override
    public Result<List<StudentVO>> queryList(Student student, PageQuery pageQuery) {
        Page<Student> page = (Page<Student>) studentService.queryList(student, pageQuery);
        List<StudentVO> studentVOS = new ArrayList<>();
        page.forEach(s -> {
            StudentVO studentVO = new StudentVO(s);
            studentVO.setStuname(userService.queryById(s.getStuId()).getUsername());
            studentVO.setClassname(classInfoService.queryById(s.getClassId()).getClassShortname());
            studentVOS.add(studentVO);
        });
        return Result.success(studentVOS).addExtra("total", page.getTotal());
    }

    @ResponseBody
    @RequiresPermissions("student:create")
    @SystemLog("班级学生管理创建角色")
    @PostMapping("/create")
    @Override
    public Result create(@Validated Student student) {
        studentService.create(student);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("student:update")
    @SystemLog("班级学生管理更新角色")
    @PostMapping("/update")
    @Override
    public Result update(@Validated Student student) {
        studentService.updateNotNull(student);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("student:delete")
    @SystemLog("班级学生管理删除角色")
    @PostMapping("/delete-batch")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id") Object[] ids) {
        super.deleteBatchByIds(ids);
        return Result.success();
    }

}
