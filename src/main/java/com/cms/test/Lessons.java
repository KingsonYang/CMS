package com.cms.test;

public class Lessons implements Runnable {

    private int ALL_LESSONS = 30;
    private int CHOOSED_NUM = 0;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                if (ALL_LESSONS <= 0) {
                    System.out.println("对不起该课程没有座位了！！");
                    break;
                }
                CHOOSED_NUM++;
                ALL_LESSONS--;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("显示抢课信息：" + Thread.currentThread().getName()
                        + "抢到第" + CHOOSED_NUM + "一个座位，剩余" + ALL_LESSONS + "个座位");
                break;
            }
        }
    }
}
