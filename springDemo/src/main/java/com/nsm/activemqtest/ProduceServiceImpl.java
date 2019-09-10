package com.nsm.activemqtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

@Service
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "activeMQQueue")
    private Destination destination;

    @Override
    public void produceMessage(String msg) {

        jmsTemplate.send(destination, (session)->{
            TextMessage textMessage = session.createTextMessage(msg);
            return textMessage;
        });
        System.out.println("现在发送的消息为：\""+msg+"\"");

    }
}
