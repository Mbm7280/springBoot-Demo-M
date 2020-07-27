package ex.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息的生产者
 */
public class P {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 从连接中创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 定义消息内容
        String message = "Hello-RabbitMq";
        // 发送消息
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        // 控制台打印
        System.out.println("[x] Sent ' " + message + " ' ");
        // 关闭通道
        channel.close();
        // 关闭连接
        conn.close();
    }


}
