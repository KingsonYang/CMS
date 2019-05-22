package com.cms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "score")
public class Score implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = User.UserUpdateChecks.class)
    private Integer id;
    private Integer stu_id;
    private Integer course_id;
    private Double score;
    private Double credit;

    public Score(){

    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public Score setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public Score setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
        return this;

    }

    public Integer getCourse_id() {
        return course_id;
    }

    public Score setCourse_id(Integer course_id) {
        this.course_id = course_id;
        return this;

    }

    public Double getScore() {
        return score;
    }

    public Score setScore(Double score) {
        this.score = score;
        return this;
    }

    public Double getCredit() {
        return credit;
    }

    public Score setCredit(Double credit) {
        this.credit = credit;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stu_id=").append(stu_id);
        sb.append(", course_id=").append(course_id);
        sb.append(", score=").append(score);
        sb.append(", credit=").append(credit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
