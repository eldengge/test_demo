package com.nsm.rabbitmqtest.fanoutExchange;

import com.nsm.rabbitmqtest.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FanoutConsumer2 {
    private final static String QUEUE_NAME = "test_queue_work2";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) {

        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtil.getConnection();
            channel = connection.createChannel();

            final Channel finalChannel = channel;

            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

            channel.basicQos(1);

            while (true){
                channel.basicConsume(QUEUE_NAME, false, "",new DefaultConsumer(finalChannel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(consumerTag);
                        String routingKey = envelope.getRoutingKey();
                        System.out.println("消费的路由键 "+routingKey);
                        String contentType = properties.getContentType();
                        System.out.println("消息类型 "+contentType);

                        long deliveryTag = envelope.getDeliveryTag();

                        String message = new String(body);
                        System.out.println("消费的消息 "+message);
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        finalChannel.basicAck(deliveryTag, true);
                    }
                });
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
