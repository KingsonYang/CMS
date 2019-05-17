package com.cms.dao;

import com.cms.entity.User;
import com.cms.entity.custom.UserInfo;
import com.cms.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper extends MyMapper<User>{

    /*int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    *//**
     * 登陆检查
     * @param id
     * @param passWord
     * @return
     *//*
    User login(int id, String passWord,int role_id);

    *//**
     * 检查数据库用户名
     * @param name
     * @return
     *//*
    User checkByName(String name);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    UserInfo selectUserInfoByID(Integer id);*/
}