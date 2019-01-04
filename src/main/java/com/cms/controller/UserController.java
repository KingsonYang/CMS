package com.cms.controller;

import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by hs on 2018.12.27.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

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




}
