package com.cms.service.impl;

import com.cms.base.exception.BizException;
import com.cms.base.service.impl.BaseService;
import com.cms.dao.UserMapper;
import com.cms.entity.User;
import com.cms.service.PasswordHelper;
import com.cms.service.RoleService;
import com.cms.service.UserService;
import com.cms.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hs on 2019.5.14.
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    @Transactional
    public void createUser(User user) {
        User u = userMapper.selectOne(new User().setUsername(user.getUsername()));
        if (u != null) {
            throw new BizException(ResultCodeEnum.FAILED_USER_ALREADY_EXIST);
        }
        // 加密密码
        passwordHelper.encryptPassword(user);
        userMapper.insertSelective(user);
    }

    @Override
    @Transactional
    public void changePassword(Long userId, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Set<String> queryRoles(String username) {
        User user = userMapper.selectOne(new User().setUsername(username));
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.queryRoles(
                Arrays.asList(user.getRoleIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList()).toArray(new Long[0])
        );
    }

    @Override
    public Set<String> queryPermissions(String username) {
        User user = userMapper.selectOne(new User().setUsername(username));
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.queryPermissions(
                Arrays.asList(user.getRoleIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList()).toArray(new Long[0])
        );
    }
}
