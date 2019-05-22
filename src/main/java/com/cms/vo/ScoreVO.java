package com.cms.vo;

import com.cms.entity.ClassInfo;
import com.cms.entity.Score;
import com.cms.entity.Student;
import com.cms.entity.User;

/**
 * Created by hs on 2019.5.18.
 */
public class ScoreVO {

    private Integer id;

    private Integer stuId;

    private Integer courseId;

    private Double score;

    private Double credit;

    private String stuname;

    private String coursename;

    public ScoreVO(){

    }

    public ScoreVO(Score score){
        this.id = score.getId();
        this.stuId = score.getStu_id();
        this.courseId = score.getCourse_id();
        this.score = score.getScore();
        this.credit = score.getCredit();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
