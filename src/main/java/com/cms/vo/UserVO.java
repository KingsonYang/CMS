package com.cms.vo;

import com.cms.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserVO {

    private Integer id; //编号
    private String username; //用户名
    private String roleIds; //拥有的角色列表
    private List<Long> roleIdList;
    private String roleNames;
    private String sex;
    private Integer age;
    private String phoneno;
    private String email;
    private String createTime;


    public UserVO() {

    }

    public UserVO(User user) {
        this.id = user.getId();
        this.username = user.getName();
        this.roleIds = user.getRoleIds();
        this.roleIdList = Arrays.asList(user.getRoleIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        this.sex = user.getSex();
        this.age = user.getAge();
        this.phoneno = user.getPhoneno();
        this.email = user.getEmail();
        this.createTime = user.getCreateTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
