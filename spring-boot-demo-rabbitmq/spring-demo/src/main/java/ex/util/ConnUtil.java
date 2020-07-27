package ex.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ConnectionUtils
 */
public class ConnUtil {

    public static Connection getConn() throws IOException, TimeoutException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("localhost");
        // 设置端口号
        factory.setPort(5672);
        // 设置账号信息
        factory.setUsername("root");
        // 设置账号密码
        factory.setPassword("admin");
        // 获取连接
        Connection conn = factory.newConnection();
        return conn;
    }

}
