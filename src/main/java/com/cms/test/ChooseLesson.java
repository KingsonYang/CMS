package com.cms.test;

public class ChooseLesson {

    private static final int MAX_NUM = 30;
    private static final int MIN_NUM = 1;
    private static final int THREAD_NUM = 1;

    private int produce = 1;

    public synchronized void produce(){

        if( this.produce > MAX_NUM){
            try {
                System.out.println("商品暂时缺货，请等待生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("第"+this.produce+"位客户购买了枸杞！");
        this.produce++;
        notifyAll();     //通知等待区的消费者可以取出产品了
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void consume()
    {
        if(this.produce <= MIN_NUM)
        {
            try
            {
                wait();
                System.out.println("缺货,稍候再取");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("消费者取走了第" + this.produce + "个产品.");
        this.produce--;
        notifyAll();   //通知等待去的生产者可以生产产品了
    }


    public static void main(String[] args) {
        ChooseLesson cl = new ChooseLesson();
        for (int i = 1; i < 40; i++) {
            cl.produce();
        }
    }
}
