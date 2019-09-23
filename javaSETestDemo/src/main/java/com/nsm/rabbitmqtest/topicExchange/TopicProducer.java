package com.nsm.rabbitmqtest.topicExchange;

import com.nsm.rabbitmqtest.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author NSM
 * @Date 2019/9/23 11:28
 * @Version 1.0
 **/
public class TopicProducer {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String message = "topic message!!";
        channel.basicPublish(EXCHANGE_NAME, "topickey.hehe", null, message.getBytes());
        System.out.println("send "+message);

        channel.close();
        connection.close();

    }
}
