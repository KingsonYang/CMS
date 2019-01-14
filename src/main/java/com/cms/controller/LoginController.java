package com.cms.controller;

import com.cms.entity.User;
import com.cms.service.UserService;
import com.cms.util.DateUtil;
import com.cms.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hs on 2018.12.27.
 */
@Controller
@RequestMapping("/index")
public class LoginController {

    @Autowired
    private UserService userService;

    //跳转首页（登录页）
    @RequestMapping("")
    public String show(){
        return "test";
    }

    @RequestMapping("/cms")
    public String cms(){
        return "list";
    }

    @ResponseBody
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public MsgUtil checkUserName(@RequestParam("name") String username){
        boolean bl = userService.checkName(username);
        if(bl){
            return MsgUtil.success();
        }else{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", "用户名已被注册");
            return MsgUtil.error().add("map", map);
        }
    }

    //登录操作
    @ResponseBody
    @RequestMapping("/Login")
    public MsgUtil login(User user, HttpServletRequest request) throws IOException {
        User u1 =userService.login(user.getName(),user.getPassword());
        if (u1==null) {
            request.getSession().setAttribute("session_user",user);//登录成功后将用户放入session中，用于拦截
            return MsgUtil.success();
        } else {
            return MsgUtil.error();
        }
    }

    //跳转注册页
    @RequestMapping("/Register")
    public String toRegister(){
        return "register";
    }

    //注册操作
    @ResponseBody
    @RequestMapping("/register")
    public MsgUtil Register(String name,String password) {
        //判断该用户名是否已被注册
        boolean u1 = userService.checkName(name);

        if (u1) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("username", "用户名已被注册");
            return MsgUtil.error().add("map", map2);
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setSex("男");
            user.setAge(18);
            user.setEmail("");
            user.setPhoneno("");
            user.setCreateTime(DateUtil.getCurrTime());
            user.setUpdateTime(DateUtil.getCurrTime());
            //1超级管理员:直接修改数据库的用户，只能打开mySQL改的
            //2普通会员:通过请求注册的用户
            //4学生
            user.setRoleId(4);
            int flag = userService.register(user);

            if (flag==1) {
                return MsgUtil.success();
            } else {
                return MsgUtil.error();
            }
        }
    }


    //测试未登陆拦截页面
    @RequestMapping("/welcome")
    public String welcome(){
            return "welcome";
    }

    //退出登录
    @RequestMapping("/SignOut")
    public String outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        return "index";
    }

}
