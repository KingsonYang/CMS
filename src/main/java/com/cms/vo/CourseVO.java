package com.cms.vo;

import com.cms.entity.Course;

import java.sql.Date;


/**
 * Created by hs on 2019.5.18.
 */
public class CourseVO{

    private Integer id;
    private Integer courseInfoId;
    private String coursename;

    private Integer teacherId;
    private String teachername;

    private Integer classId;
    private String classname;

    private Integer classroomId;
    private String classroomname;

    private Integer teachTime;
    private String beginDate;
    private String endDate;
    private Integer seat;



    public CourseVO(){

    }

    public CourseVO(Course course){
        this.id = course.getId();
        this.courseInfoId = course.getCourseInfoId();
        this.teacherId = course.getTeacherId();
        this.classId = course.getClassId();
        this.classroomId = course.getClassroomId();
        this.teachTime = course.getTeachTime();
        this.beginDate = course.getBeginDate();
        this.endDate = course.getEndDate();
        this.seat = course.getSeat();

    }


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

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomname() {
        return classroomname;
    }

    public void setClassroomname(String classroomname) {
        this.classroomname = classroomname;
    }

    public Integer getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(Integer teachTime) {
        this.teachTime = teachTime;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
