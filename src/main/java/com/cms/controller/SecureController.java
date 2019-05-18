package com.cms.controller;

import com.cms.base.controller.BaseCrudController;
import com.cms.entity.User;
import com.cms.service.UserService;
import com.cms.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hs on 2019.5.18.
 */
@Controller
@RequestMapping("/")
public class SecureController extends BaseCrudController<User> {


    @Autowired
    private UserService userService;

    @GetMapping
    @RequiresPermissions("user:view")
    @RequestMapping("/updatePwd")
    public String changePwdPage(){


        return "system/changePwd";
    }

    @ResponseBody
    @GetMapping("/checkOldPwd")
    public void checkOldPwd(String newPassword,Model model){
        Long userId = (Long) SecurityUtils.getSubject().getSession().getAttribute("currentUserId");
        userService.changePassword(userId,newPassword);
    }

    @ResponseBody
    @PostMapping("/updatePwd/update")
    public Result changePassword(@RequestParam(value = "password", required = true) String newPassword){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String userId = (String) session.getAttribute("currentUserId");
        userService.changePassword(Long.valueOf(userId),newPassword);
        return Result.success();

    }
}
