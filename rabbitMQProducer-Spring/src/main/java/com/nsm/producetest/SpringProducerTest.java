package com.nsm.producetest;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author NSM
 * @Date 2019/9/25 14:59
 * @Version 1.0
 **/
public class SpringProducerTest {

    public static void main(String[] args) throws InterruptedException {
        Gson gson = new Gson();
        Student student = new Student("Billy","28");
        String json = gson.toJson(student);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        RabbitTemplate template = applicationContext.getBean("mqTemplate", RabbitTemplate.class);
        template.convertAndSend("key111",json);
        Map<String,Object> map = new HashMap<>();
        map.put("xiaoming", student);
        template.convertAndSend("key222",map);
        TimeUnit.SECONDS.sleep(2);
        applicationContext.destroy();
    }
}
