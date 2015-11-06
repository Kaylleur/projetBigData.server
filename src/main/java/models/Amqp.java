package models;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Thomas on 01/11/2015.
 */
public class Amqp {

    public final static String QUEUE_NAME = "hello";

    public static Channel connect()throws Exception{
        //get variable
        String uri = System.getenv("cloud_amqp").split(";")[0];
        String username = System.getenv("cloud_amqp").split(";")[1];
        String password = System.getenv("cloud_amqp").split(";")[2];

        //initialize channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        factory.setUsername(username);
        factory.setPassword(password);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        return channel;
    }
}
