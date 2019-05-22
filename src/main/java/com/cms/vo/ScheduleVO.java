package com.cms.vo;

import com.cms.entity.Course;

/**
 * Created by hs on 2019.5.19.
 */
public class ScheduleVO {

    private String mon;
    private String tues;
    private String wes;
    private String thur;
    private String fri;
    private String sat;
    private String sun;

    private String tt;

    public ScheduleVO(){

    }

    public ScheduleVO(Course course){

    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTues() {
        return tues;
    }

    public void setTues(String tues) {
        this.tues = tues;
    }

    public String getWes() {
        return wes;
    }

    public void setWes(String wes) {
        this.wes = wes;
    }

    public String getThur() {
        return thur;
    }

    public void setThur(String thur) {
        this.thur = thur;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }
}
