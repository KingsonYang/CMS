package com.cms.service;

import com.cms.entity.User;
import com.cms.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:yqs
 * @Date: 2018/12/27
 * @Time: 20:45
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User selectAll(){
        return userMapper.selectAll();
    }

    public User selectById(int id){
        return userMapper.selectById(id);
    }


}
