package com.cms.dao;

import com.cms.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by hs on 2018.12.27.
 */
@Repository
public interface UserMapper {

    Map<User , String> selectAll();

    User selectById(int id);

    User login(String userName,String passWord);

    int register(User user);
}
