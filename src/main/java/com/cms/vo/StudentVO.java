package com.cms.vo;

import com.cms.entity.ClassInfo;
import com.cms.entity.Student;
import com.cms.entity.User;

/**
 * Created by hs on 2019.5.18.
 */
public class StudentVO {

    private Integer id;

    private Integer classId;

    private Integer stuId;

    private String stuname;

    private String classname;

    private User user;

    private ClassInfo classInfo;

    public StudentVO(){

    }

    public StudentVO(Student student){
        this.id = student.getId();
        this.classId = student.getClassId();
        this.stuId = student.getStuId();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }
}
