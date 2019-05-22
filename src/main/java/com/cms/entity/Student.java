package com.cms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "student")
public class Student implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = User.UserUpdateChecks.class)
    private Integer id;

    private Integer stuId;

    private Integer classId;

    private static final long serialVersionUID = 1L;


    public Student(){

    }



    public Integer getId() {
        return id;
    }

    public Student setId(Integer id) {
        this.id = id;
        return this;
    }


    public Integer getStuId() {
        return stuId;
    }


    public Student setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }


    public Integer getClassId() {
        return classId;
    }


    public Student setClassId(Integer classId) {
        this.classId = classId;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stuId=").append(stuId);
        sb.append(", classId=").append(classId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}