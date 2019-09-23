package com.nsm.rabbitmqtest.topicExchange;

import com.nsm.rabbitmqtest.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author NSM
 * @Date 2019/9/23 11:24
 * @Version 1.0
 **/
public class TopicConsumer1 {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    private final static String QUEUE_NAME = "test_queue_topic1";

    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"topickey.*");

        while (true){
            channel.basicConsume(QUEUE_NAME, false, "", new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("路由键:"+envelope.getRoutingKey());
                    String message = new String(body);
                    System.out.println("消费的消息："+message);
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, true);
                }
            });
        }
    }
}
