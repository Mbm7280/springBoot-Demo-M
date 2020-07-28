package ex.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 信息的创建者
 */
public class P {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 1.创建连接
        Connection conn = ConnUtil.getConn();
        // 2.创建通道
        Channel channel = conn.createChannel();
        // 3.创建交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        // 4.发送消息
        String message = "Hello_World_Topic";
        channel.basicPublish(EXCHANGE_NAME,"item.update",null,message.getBytes());
        System.out.println("[Sent] ' " + message + " ' ");
        // 5.关闭
        channel.close();
        conn.close();

    }

}
