package com.nsm.activemqtest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * queue模式生产者
 */
public class MessageProducer {

    //定义ActivMQ的连接地址
    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //定义发送消息的队列名称
    private static final String QUEUE_NAME = "MyMessage";

    public static void produceMessage(){
        //建立工厂
        ActiveMQConnectionFactory factory = null;
        //建立连接
        Connection connection = null;
        try {
            factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
            connection = factory.createConnection();
            connection.start();
            //创建session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建队列目标
            Destination destination = session.createQueue(QUEUE_NAME);

            javax.jms.MessageProducer producer = session.createProducer(destination);

            for (int i = 1;i<=10;++i){
                TextMessage textMessage = session.createTextMessage("我的第"+i+"条消息");
                producer.send(textMessage);
                System.out.println(textMessage.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        produceMessage();
    }

}
