package com.cms.controller;

import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.entity.ClassroomInfo;
import com.cms.service.ClassroomInfoService;
import com.cms.util.Result;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/classroom")
public class ClassroomInfoController extends BaseCrudController<ClassroomInfo> {

    @Autowired
    private ClassroomInfoService classroomInfoService;

    @RequiresPermissions("classroom:view")
    @GetMapping
    public String resourcePage(Model model) {
        PageHelper.orderBy("id");
        model.addAttribute("classroomList", classroomInfoService.queryAll());
        return "system/classroom";
    }

    @ResponseBody
    @RequiresPermissions("classroom:create")
    @SystemLog("教室管理创建资源")
    @PostMapping("/create")
    @Override
    public Result create(@Validated ClassroomInfo classroomInfo) {
        classroomInfoService.create(classroomInfo);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("classroom:update")
    @SystemLog("教室管理更新资源")
    @PostMapping("/update")
    @Override
    public Result update(@Validated ClassroomInfo classroomInfo) {
        classroomInfoService.updateNotNull(classroomInfo);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("classroom:delete")
    @SystemLog("课程管理删除资源")
    @PostMapping("/delete")
    @Override
    public Result delete(@RequestParam("id") Object id) {
        super.delete(id);
        return Result.success();
    }

}
