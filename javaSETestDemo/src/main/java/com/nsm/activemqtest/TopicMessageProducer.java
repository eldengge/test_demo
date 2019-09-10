package com.nsm.activemqtest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.jms.MessageProducer;

/**
 * topic模式生产者
 */
public class TopicMessageProducer {

    //定义ActivMQ的连接地址
    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //定义发送消息的队列名称
    private static final String TOPIC_NAME = "MyTopicMessage";

    public static void producerMessage(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(TOPIC_NAME);
            MessageProducer producer = session.createProducer(destination);
            for (int i = 1;i<=100;++i){
                TextMessage textMessage = session.createTextMessage("我的第"+i+"条Topic消息");
                System.out.println(textMessage.getText());
                producer.send(textMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        producerMessage();
    }
}
