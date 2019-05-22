package com.cms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


@Table(name = "course")
public class Course implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = CourseInfo.UserUpdateChecks.class)
    private Integer id;
    private Integer courseInfoId;
    private Integer teacherId;
    private Integer classId;
    private Integer classroomId;
    private Integer teachTime;
    private String beginDate;
    private String endDate;
    private Integer seat;

    private static final long serialVersionUID = 1L;

    public Course(){

    }

    public Integer getId() {
        return id;
    }

    public Course setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCourseInfoId() {
        return courseInfoId;
    }

    public Course setCourseInfoId(Integer courseInfoId) {
        this.courseInfoId = courseInfoId;
        return this;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public Course setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public Integer getClassId() {
        return classId;
    }

    public Course setClassId(Integer classId) {
        this.classId = classId;
        return this;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public Course setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
        return this;
    }

    public Integer getTeachTime() {
        return teachTime;
    }

    public Course setTeachTime(Integer teachTime) {
        this.teachTime = teachTime;
        return this;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public Course setBeginDate(String beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public Course setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getSeat() {
        return seat;
    }

    public Course setSeat(Integer seat) {
        this.seat = seat;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseInfoId=").append(courseInfoId);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", classId=").append(classId);
        sb.append(", classroomId=").append(classroomId);
        sb.append(", teachTime=").append(teachTime);
        sb.append(", beginDate=").append(beginDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", seat=").append(seat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}