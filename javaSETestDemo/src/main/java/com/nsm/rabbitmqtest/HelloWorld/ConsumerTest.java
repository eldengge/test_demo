package com.nsm.rabbitmqtest.HelloWorld;

import com.nsm.rabbitmqtest.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConsumerTest {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) {

        Connection connection = null;

        Channel channel = null;


        try {
            connection = ConnectionUtil.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicQos(1);

            // 定义队列的消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);

            // 监听队列
            channel.basicConsume(QUEUE_NAME,false,consumer);

            while(true){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("消费 "+message);
                TimeUnit.SECONDS.sleep(2);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
