package com.cms.controller;


import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.entity.ClassInfo;
import com.cms.service.ClassInfoService;
import com.cms.util.Result;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/class")
public class ClassInfoController extends BaseCrudController<ClassInfo>{

    @Autowired
    private ClassInfoService classInfoService;

    @RequiresPermissions("class:view")
    @GetMapping
    public String resourcePage(Model model) {
        PageHelper.orderBy("id");
        model.addAttribute("classList", classInfoService.queryAll());
        return "system/class";
    }

    @ResponseBody
    @RequiresPermissions("class:create")
    @SystemLog("班级管理创建资源")
    @PostMapping("/create")
    @Override
    public Result create(@Validated ClassInfo classInfo) {
        classInfoService.create(classInfo);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("class:update")
    @SystemLog("班级管理更新资源")
    @PostMapping("/update")
    @Override
    public Result update(@Validated ClassInfo classInfo) {
        classInfoService.updateNotNull(classInfo);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("class:delete")
    @SystemLog("班级管理删除资源")
    @PostMapping("/delete")
    @Override
    public Result delete(@RequestParam("id") Object id) {
        super.delete(id);
        return Result.success();
    }


}
