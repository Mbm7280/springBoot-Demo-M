package ex.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息的生产者
 * 轮询分发
 */
public class P {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection conn = ConnUtil.getConn();

        Channel channel = conn.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i = 0; i <= 50 ; i++) {
            String message = "m:" + i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("[x] Sent ' "+ message + " ' ");
            Thread.sleep(i * 30);
        }
        channel.close();
        conn.close();
    }

}
