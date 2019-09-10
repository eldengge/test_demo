package com.nsm.activemqtest;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ProducerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("producer.xml");
        ProduceService produceService = classPathXmlApplicationContext.getBean("produceServiceImpl", ProduceService.class);
        for (int i = 1;i<=100;i++){
            produceService.produceMessage("第"+i+"条消息");
        }
    }
}
