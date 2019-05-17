package com.cms.service.impl;

import com.cms.base.service.impl.BaseService;
import com.cms.dao.RoleMapper;
import com.cms.entity.Role;
import com.cms.service.ResourceService;
import com.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hs on 2019.5.14.
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public Set<String> queryRoles(Long... roleIds) {
        Set<String> roles = new HashSet<>();
        for (Long roleId : roleIds){
            Role role = roleMapper.selectByPrimaryKey(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> queryPermissions(Long[] roleIds) {
        Weekend<Role> weekend = Weekend.of(Role.class);
        weekend.weekendCriteria().andIn(Role::getId, Arrays.asList(roleIds));
        return resourceService.queryPermissions(
                roleMapper.selectByExample(weekend).stream().flatMap(r ->
                        Arrays.asList(r.getResourceIds().split(",")).stream()
                ).map(Long::valueOf).collect(Collectors.toSet())
        );
    }
}
