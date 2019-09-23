package com.nsm.rabbitmqtest.directExchange;

import com.nsm.rabbitmqtest.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectProducer {
    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message = "给key1的消息";
        channel.basicPublish(EXCHANGE_NAME,"key1", null, message.getBytes());
        message = "给key2的消息";
        channel.basicPublish(EXCHANGE_NAME,"key2", null, message.getBytes());

        channel.close();
        connection.close();
    }
}
