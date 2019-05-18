package com.cms.entity;

import com.cms.entity.custom.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Table(name = "class_info")
public class ClassInfo implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = CourseInfo.UserUpdateChecks.class)
    private Integer id;

    private String schoolName;
    private String deptName;
    private String className;
    private String classShortname;


    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public ClassInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public ClassInfo setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
        return this;
    }

    public String getDeptName() {
        return deptName;
    }

    public ClassInfo setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ClassInfo setClassName(String className) {
        this.className = className == null ? null : className.trim();
        return this;
    }

    public String getClassShortname() {
        return classShortname;
    }

    public ClassInfo setClassShortname(String classShortname) {
        this.classShortname = classShortname == null ? null : classShortname.trim();
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", deptName=").append(deptName);
        sb.append(", className=").append(className);
        sb.append(", classShortname=").append(classShortname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}