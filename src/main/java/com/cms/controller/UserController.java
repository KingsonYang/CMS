package com.cms.controller;

import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by hs on 2018.12.27.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUser/{id}")
    public String getUserById(@PathVariable int id){
        return userService.selectById(id).toString();
    }

    @RequestMapping("/getAllUser")
    public String getAllUser(){
        Map<User,String> users = userService.selectAll();

        return users.get(0).toString();
    }

}
