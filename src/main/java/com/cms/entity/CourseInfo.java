package com.cms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "course_info")
public class CourseInfo{


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = UserUpdateChecks.class)
    private Integer id;

    private String name;
    private String description;
    private Double credit;
    private Integer act;

    public interface UserCreateChecks {

    }

    public interface UserUpdateChecks {

    }

    public CourseInfo(){

    }

    public Integer getId() {
        return id;
    }

    public CourseInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseInfo setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseInfo setDescription(String description) {
        this.description = description == null ? null : description.trim();
        return this;
    }

    public Double getCredit() {
        return credit;
    }

    public CourseInfo setCredit(Double credit) {
        this.credit = credit;
        return this;
    }

    public Integer getAct() {
        return act;
    }

    public CourseInfo setAct(Integer act) {
        this.act = act;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", dec=").append(description);
        sb.append(", credit=").append(credit);
        sb.append(", character=").append(act);
        sb.append("]");
        return sb.toString();
    }
}