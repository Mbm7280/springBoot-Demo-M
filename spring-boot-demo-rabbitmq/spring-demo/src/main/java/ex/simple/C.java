package ex.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息的消费者
 */
public class C {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
        // 获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received ' " + message + " ' ");
        }

    }

}
