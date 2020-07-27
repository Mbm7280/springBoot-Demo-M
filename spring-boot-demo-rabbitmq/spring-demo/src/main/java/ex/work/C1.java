package ex.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 第一个消息消费者
 */
public class C1 {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection conn = ConnUtil.getConn();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);

        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received_2 ' " + message + " ' ");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }

}
