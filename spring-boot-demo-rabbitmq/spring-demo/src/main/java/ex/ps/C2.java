package ex.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息的消费者2
 */
public class C2 {

    private final static String QUEUE_NAME = "test_queue_ps2";
    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 1.获取连接
        Connection conn = ConnUtil.getConn();
        // 2.创建通道
        Channel channel = conn.createChannel();
        // 3.创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 4.绑定交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        // 5.创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 6.监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);

        // 7.获取消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[前端系统] ' " + message + " ' " );
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }

}
