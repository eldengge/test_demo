package com.nsm.consumetest;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author NSM
 * @Date 2019/9/26 15:35
 * @Version 1.0
 **/
@Service
public class TestConsumerService2 {

    //方法级别消费 用String接收
    public void getMassage(String map){
        System.out.println("service2："+map);
    }

}
