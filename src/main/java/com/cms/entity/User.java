package com.cms.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "user")
public class User {


    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = UserUpdateChecks.class)
    private Integer id;

    @NotNull(message = "用户名不能为空")
    @Min(value = 3, message = "用户名不能低于3位")
    private String username;

    @NotNull(message = "用户名不能为空")
    @Min(value = 3, message = "用户名不能低于3位")
    private String name;


    @NotBlank(message = "密码不能为空", groups = UserCreateChecks.class)
    @Min(value = 6, message = "密码不能低于6位", groups = UserCreateChecks.class)
    private String password;

    /**
     * 加密密码的盐
     */
    private String salt;
    private String sex;
    private Integer age;
    private String phoneno;
    private String email;
    private String roleIds;
    private String createTime;
    private String updateTime;

    public interface UserCreateChecks {

    }

    public interface UserUpdateChecks {

    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }


    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public User setUsername(String username){
        this.username = username;
        return this;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRoleIds() {
        return roleIds;
    }

    public User setRoleIds(String roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", phoneno=").append(phoneno);
        sb.append(", email=").append(email);
        sb.append(", roleIds=").append(roleIds);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}