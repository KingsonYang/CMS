package com.cms.controller;

import com.cms.entity.User;
import com.cms.entity.custom.UserInfo;
import com.cms.service.UserService;
import com.cms.util.DateUtil;
import com.cms.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by hs on 2018.12.27.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 跳转用户信息页面
     * @return
     */
    @RequestMapping("/user")
    public String showUserInfo(Model model,HttpServletRequest request){
        User u1 = (User) request.getSession().getAttribute("session_user");
        UserInfo userInfo = userService.getUserInfo(u1.getId());
        DateUtil.getCurrTime(userInfo.getCreateTime());
        model.addAttribute("userInfo",userInfo);
        return "UserManage/UserInfo";
//        return userInfo.toString();
    }

    /**
     * 跳转修改密码页面
     * @return
     */
    @RequestMapping("/change_pwd")
    public String changePwd(){
        return "UserManage/UserSecret";
    }

    @RequestMapping(value = "/userInfo")
    public String showUserInfo(HttpServletRequest request){
        User u1 = (User) request.getSession().getAttribute("session_user");
        return userService.selectById(u1.getId()).toString();
    }

    @RequestMapping("/{id}")
    public String getUserById(@PathVariable int id){
        return userService.selectById(id).toString();
    }

    @RequestMapping("/list")
    public String allUser(Model model){
        List<User> list = userService.selectAll();
        model.addAttribute("users",list);
        return "list";
    }


    /**
     * 修改密码
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/changePwd")
    public MsgUtil new_password(String password, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        user.setPassword(password);
        int flag =  userService.new_password(user);
        if(flag == 1){
            return MsgUtil.success();
        }else{
            return MsgUtil.error();
        }
    }

    /**
     * 检查旧密码是否填写正确
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkOldPwd")
    public MsgUtil checkOldPwd(String password, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        User u1 =userService.login(user.getId(),password,user.getRoleId());
        if (u1!=null) {
            return MsgUtil.success();
        } else {
            return MsgUtil.error("map", null);
        }
    }




}
