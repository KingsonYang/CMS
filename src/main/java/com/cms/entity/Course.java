package com.cms.entity;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {

    private Integer id;
    private Integer courseInfoId;
    private Integer teacherId;
    private Integer classId;
    private Integer classroomId;
    private Integer teachTime;
    private Date beginDate;
    private Date endDate;
    private Integer seat;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(Integer courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(Integer teachTime) {
        this.teachTime = teachTime;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
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