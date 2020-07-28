package ex.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息的生产者
 */
public class P {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 1.创建连接
        Connection conn = ConnUtil.getConn();
        // 2.创建通道
        Channel channel = conn.createChannel();

        // 3.声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        // 4.定义消息
        String message = "Hello_World_Ps";

        // 5.发送消息
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[Sent] ' " + message + " ' ");

        // 6.关闭连接
        channel.close();
        conn.close();

    }

}
