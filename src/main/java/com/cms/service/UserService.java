package com.cms.service;

import com.cms.entity.User;
import com.cms.dao.UserMapper;
import com.cms.util.DateUtil;
import com.cms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:yqs
 * @Date: 2018/12/27
 * @Time: 20:45
 */
@Service
public class UserService{

    @Autowired
    UserMapper userMapper;

    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    public User selectById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    public boolean checkName(String username){
        return userMapper.checkByName(username) != null ? true : false;
    }

    public User login(String userName, String passWord) {
        return userMapper.login(userName,MD5Util.getMD5(passWord.getBytes()));
    }

    @Transactional
    public int register(User user) {
        user.setPassword(MD5Util.getMD5(user.getPassword().getBytes()));
        return userMapper.insert(user);
    }

    /**
     * 根据主键修改密码
     * @param user
     * @return
     */
    public int new_password(User user){
        user.setUpdateTime(DateUtil.getCurrTime());
        user.setPassword(MD5Util.getMD5(user.getPassword().getBytes()));
        return userMapper.updateByPrimaryKey(user);
    }

    //查询用户所有信息
    public void getUserInfo(){
        //查询学生基本信息
        //查询学生的班级信息
    }


}
