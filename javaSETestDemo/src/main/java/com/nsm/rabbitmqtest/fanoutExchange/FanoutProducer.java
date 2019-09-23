package com.nsm.rabbitmqtest.fanoutExchange;

import com.nsm.rabbitmqtest.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutProducer {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtil.getConnection();
            channel = connection.createChannel();
            // 声明exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            for(int i = 1;i<=10;++i){
                String message = "fanout message"+i;
                channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
                System.out.println("发送"+message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

            if(connection!= null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
