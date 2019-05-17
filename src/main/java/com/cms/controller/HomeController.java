package com.cms.controller;

import com.cms.base.controller.BaseController;
import com.cms.dto.ResourceDto;
import com.cms.entity.User;
import com.cms.service.ResourceService;
import com.cms.service.UserService;
import com.cms.service.UserService;
import com.cms.util.Constants;
import com.cms.util.MsgUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;

/**
 * Created by hs on 2018.12.27.
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    @Autowired
    private ResourceService resourceService;

    /*@Autowired
    private UserService userService;*/

    @Autowired
    private UserService userService;

    Map map = new HashMap();

    @RequestMapping("/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        logger.info("begin to login");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "登陆失败次数过多";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping("/")
    public String cms(Model model){ //返回给前台列表信息
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Set<String> permissions = userService.queryPermissions(username);
        List<ResourceDto> menus = resourceService.findMenus(permissions);
        StringBuilder dom = new StringBuilder();
        getMenuTree(menus, Constants.MENU_ROOT_ID, dom);
        model.addAttribute(Constants.MENU_TREE, dom);
        return "base/main";
    }

    private List<ResourceDto> getMenuTree(List<ResourceDto> source, Long pid, StringBuilder dom) {
        List<ResourceDto> target = getChildResourceByPId(source, pid);
        target.forEach(res -> {
            dom.append("<li class='treeview'>");
            dom.append("<a href='" + res.getUrl() + "'>");
            dom.append("<i class='" + res.getIcon() + "'></i>");
            dom.append("<span>" + res.getName() + "</span>");
            if (Constants.SHARP.equals(res.getUrl())) {
                dom.append("<span class='pull-right-container'><i class='fa fa-angle-left pull-right'></i> </span>");
            }
            dom.append("</a>");
            dom.append("<ul class='treeview-menu'>");
            res.setChildren(getMenuTree(source, res.getId(), dom));
            dom.append("</ul>");
            dom.append("</li>");
        });
        return target;
    }

    private List<ResourceDto> getChildResourceByPId(List<ResourceDto> source, Long pId) {
        List<ResourceDto> child = new ArrayList<>();
        source.forEach(res -> {
            if (pId.equals(res.getParentId())) {
                child.add(res);
            }
        });
        return child;
    }

    /*@ResponseBody
    @RequestMapping(value = "/checkNameIsRegister",method = RequestMethod.GET)
    public MsgUtil checkUserName(@RequestParam("name") String username){
        boolean bl = userService.checkName(username);
        if(bl){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", "用户名已被注册");
            return MsgUtil.error("map", map);
        }else{
            return MsgUtil.success();
        }
    }*/

    /**
     /** 登陆管理系统
     * @param user
     * @param request
     * @return
     * @throws
     *//*
    @ResponseBody
    @RequestMapping("/Login")
    public MsgUtil login(User user, HttpServletRequest request){
        *//*logger.info("begin to login");
        User u1 =userService.login(user.getId(),user.getPassword(),user.getRoleId());
        if (u1!=null) {
            request.getSession().setAttribute("session_user",u1);//登录成功后将用户放入session中，用于拦截
            map.put("login_user",u1);
            return MsgUtil.success().add("login_user",map);
        } else {
            map.put("msg","请检查用户名密码重新登陆！");
            return MsgUtil.error("map", map);
        }*//*
    }*/

    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping("/Register")
    public String toRegister(){
        return "register";
    }

    /**
     * 添加用户
     * @param name
     * @param password
     * @return
     *//*
    @ResponseBody
    @RequestMapping("/register")
    public MsgUtil Register(String name,String password) {

           *//* User user = new User();
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
                return MsgUtil.error("map", null);
            }*//*

    }*/


    /**
     * 跳转管理系统主页
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(){
            return "welcome";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/SignOut")
    public String outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        return "login";
    }

}
