package com.nsm.consumetest;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

/**
 * @Author NSM
 * @Date 2019/9/26 15:17
 * @Version 1.0
 **/
@Service
public class TestConsumeService implements ChannelAwareMessageListener {
    //自动应答模式下实现MessageListener接口即可
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("接收到的消息："+message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
