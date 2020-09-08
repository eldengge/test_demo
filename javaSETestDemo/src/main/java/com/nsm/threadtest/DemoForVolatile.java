package com.nsm.threadtest;

import java.util.concurrent.TimeUnit;

/**
 * @Author NSM
 * @Date 2019/10/31 14:55
 * @Version 1.0
 **/
public class DemoForVolatile{

    public volatile int a=0;

    public void  testVolatitle(){
        Thread t1 = new Thread(()->{
            for (;;){
                ++a;
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(()->{
            for(;;){
                System.out.println( Thread.currentThread().getName()+":"+ a);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");
        Thread t3 = new Thread(()->{
            for(;;){
                System.out.println( Thread.currentThread().getName()+":"+ a);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t3");
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        DemoForVolatile d = new DemoForVolatile();
        d.testVolatitle();
    }
}
