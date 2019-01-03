package com.cms.controller;

import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        return "index";
    }

    //登录操作
    @ResponseBody
    @RequestMapping("/Login")
    public String login(User user, HttpServletRequest request,HttpServletResponse response) throws IOException {
        String userName = user.getName();
        String passWord = user.getPassword();
        User u1 =userService.login(userName,passWord);
        if (u1==null){
            return "用户名或密码错误";
        }else{
            request.getSession().setAttribute("session_user",user);//登录成功后将用户放入session中，用于拦截
            response.sendRedirect("welcome");
            return "welcome";
        }
    }

    //跳转注册页
    @RequestMapping("/Register")
    public String toRegister(){
        return "register";
    }

    //注册操作
    @RequestMapping("/registered")
    public String register(User user){
        int su = userService.register(user);
        if(su==0){
            System.out.println("----");
        }
        return "welcome";
    }

    //测试未登陆拦截页面
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response) throws IOException{
            return "welcome";
    }

    //退出登录
    @RequestMapping("/SignOut")
    public String outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        return "index";
//        response.sendRedirect("");
    }

}
