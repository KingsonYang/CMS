package com.cms.service;

import com.cms.entity.User;
import com.cms.dao.UserMapper;
import com.cms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Map;

/**
 * @Author:yqs
 * @Date: 2018/12/27
 * @Time: 20:45
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Map<User,String> selectAll(){
        return userMapper.selectAll();
    }

    public User selectById(int id){
        return userMapper.selectById(id);
    }

    public User login(String userName, String passWord) {

        return userMapper.login(userName,MD5Util.getMD5(passWord.getBytes()));
    }

    public int register(User user) {
        user.setPassWord(MD5Util.getMD5(user.getPassWord().getBytes()));
        return userMapper.register(user);
    }

}
