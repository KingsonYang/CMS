package com.cms.service;

import com.cms.base.service.IService;
import com.cms.entity.Role;

import java.util.Set;

/**
 * Created by hs on 2019.5.14.
 */
public interface RoleService extends IService<Role>{

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> queryRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> queryPermissions(Long[] roleIds);
}
