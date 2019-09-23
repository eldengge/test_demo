package com.nsm.rabbitmqtest.HelloWorld;

import com.nsm.rabbitmqtest.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerTest {

    public static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args){
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtil.getConnection();
            //创建通道
            channel = connection.createChannel();
            //创建队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for(int i = 1;i<=20;++i){
                String message = "Hello World!"+i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("send " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
          if(channel!=null){
              try {
                  channel.close();
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (TimeoutException e) {
                  e.printStackTrace();
              }
          }
          if(connection != null){
              try {
                  connection.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
        }

    }
}
