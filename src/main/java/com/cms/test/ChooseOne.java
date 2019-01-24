package com.cms.test;

public class ChooseOne {
    public static void main(String[] args) {

        Lessons lessons = new Lessons();

        Thread tom = null;
        for (int i = 1; i < 40; i++) {
            tom = new Thread(lessons,"tom"+i);
            tom.start();
        }
    }
}
