package com.cms.controller;

import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.base.query.PageQuery;
import com.cms.entity.Role;
import com.cms.entity.User;
import com.cms.service.RoleService;
import com.cms.service.UserService;
import com.cms.util.Result;
import com.cms.util.ResultCodeEnum;
import com.cms.vo.UserVO;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * Created by hs on 2018.12.27.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseCrudController<User>{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping
    @RequiresPermissions("user:view")
    public String userPage(Model model) {
        model.addAttribute("roleList", roleService.queryAll());
        return "system/user";
    }


    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("user:view")
    @Override
    public Result<List<UserVO>> queryList(User user, PageQuery pageQuery) {
        Page<User> page = (Page<User>) userService.queryList(user, pageQuery);
        List<UserVO> userVOS = new ArrayList<>();
        page.forEach(u -> {
            UserVO userVO = new UserVO(u);
            userVO.setRoleNames(getRoleNames(userVO.getRoleIdList()));
            userVOS.add(userVO);
        });
        return Result.success(userVOS).addExtra("total", page.getTotal());
    }


    private String getRoleNames(Collection<Long> groupIds) {
        if (CollectionUtils.isEmpty(groupIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Long roleId : groupIds) {
            Role role = roleService.queryById(roleId);
            if (role != null) {
                s.append(role.getDescription());
                s.append(",");
            }
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }


    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("user:create")
    @SystemLog("用户管理创建用户")
    @Override
    public Result create(@Validated(User.UserCreateChecks.class) User user) {
        user.setName(user.getUsername());
        userService.createUser(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("user:update")
    @SystemLog("用户管理更新用户")
    @Override
    public Result update(@Validated(User.UserUpdateChecks.class) User user) {
        userService.updateNotNull(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete-batch")
    @RequiresPermissions("user:delete")
    @SystemLog("用户管理删除用户")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id") Object[] ids) {
        // 当前用户
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.queryOne(new User().setUsername(username));
        boolean isSelf = Arrays.stream(ids).anyMatch(id -> id.equals(user.getId()));
        if (isSelf) {
            return Result.failure(ResultCodeEnum.FAILED_DEL_OWN);
        }
        super.deleteBatchByIds(ids);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("user:update")
    @PostMapping("/{id}/change/password")
    @SystemLog("用户管理更改用户密码")
    public Result changePassword(@PathVariable("id") Long id, String newPassword) {
        userService.changePassword(id, newPassword);
        return Result.success();
    }




}
