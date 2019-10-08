package com.nsm.consumetest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author NSM
 * @Date 2019/9/26 15:28
 * @Version 1.0
 **/
public class TestConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    }
}
