package com.cms.enums;

/**
 * Created by hs on 2019.5.17.
 */
public enum CourseInfoAct {

    X("必修课"), O("选修课");

    private final String info;

    private CourseInfoAct(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }


}
