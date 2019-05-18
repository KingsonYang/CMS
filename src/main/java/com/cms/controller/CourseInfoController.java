package com.cms.controller;

import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.entity.Course;
import com.cms.entity.CourseInfo;
import com.cms.entity.custom.PageEntity;
import com.cms.service.CourseInfoService;
import com.cms.util.MsgUtil;
import com.cms.util.Result;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/courseinfo")
public class CourseInfoController extends BaseCrudController<CourseInfo>{

    @Autowired
    private CourseInfoService courseInfoService;

    @RequiresPermissions("courseinfo:view")
    @GetMapping
    public String resourcePage(Model model) {
        PageHelper.orderBy("id");
        model.addAttribute("courseinfoList", courseInfoService.queryAll());
        return "system/courseinfo";
    }

    @ResponseBody
    @RequiresPermissions("courseinfo:create")
    @SystemLog("课程管理创建资源")
    @PostMapping("/create")
    @Override
    public Result create(@Validated CourseInfo courseInfo) {
        courseInfoService.create(courseInfo);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("courseinfo:update")
    @SystemLog("课程管理更新资源")
    @PostMapping("/update")
    @Override
    public Result update(@Validated CourseInfo courseInfo) {
        courseInfoService.updateNotNull(courseInfo);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("courseinfo:delete")
    @SystemLog("课程管理删除资源")
    @PostMapping("/delete")
    @Override
    public Result delete(@RequestParam("id") Object id) {
        super.delete(id);
        return Result.success();
    }

}
