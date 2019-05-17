package com.cms.service;

import com.cms.base.exception.BizException;
import com.cms.base.service.IService;
import com.cms.entity.User;

import java.util.Set;

/**
 * Created by hs on 2019.5.14.
 */
public interface UserService extends IService<User>{

    /**
     * 创建用户
     * @param user
     */
    void createUser(User user) throws BizException;

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> queryRoles(String username);


    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> queryPermissions(String username);
}
