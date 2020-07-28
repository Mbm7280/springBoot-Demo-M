package ex.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import ex.util.ConnUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息的生产者
 */
public class P {

    private final static String EXCHANGE_NAME = "test_exchange_routing";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 1.获取连接
        Connection conn = ConnUtil.getConn();
        // 2.创建通道
        Channel channel = conn.createChannel();
        // 3.生命交换机
        // String exchange
        // String typw
        // boolean durable
        // boolean autoDelete
        // boolean internal
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        // 4.消息内容
        String message1 = "Hello_World_Update";
        String message2 = "Hello_World_Insert";
        String message3 = "Hello_World_Delete";

        //  5.推送消息
        // String exchange
        // String routingKey
        // BasicProperties props
        // byte[] body
        channel.basicPublish(EXCHANGE_NAME,"update",null,message1.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message2.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "delete",null,message3.getBytes());
        // 6.关闭连接
        channel.close();
        conn.close();
    }

}
