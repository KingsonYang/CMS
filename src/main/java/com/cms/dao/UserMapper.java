package com.cms.dao;

import com.cms.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by hs on 2018.12.27.
 */
@Repository
public interface UserMapper {

    User selectAll();
    User selectById(int id);
}
