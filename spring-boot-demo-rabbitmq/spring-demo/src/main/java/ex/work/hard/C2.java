package ex.work.hard;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class C2 {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 创建 通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 同一时刻服务器只会发一条消息给消费者
        // 打破轮询规则
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received_2 ' " + message + " ' ");
            Thread.sleep(1000);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }

}
