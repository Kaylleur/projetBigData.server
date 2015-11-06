package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import models.Amqp;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Thomas on 01/11/2015.
 */
public class Receive {
    
    /**
     * Consumne messages from amqp
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Amqp.connect(Amqp.QUEUE_MODEL);
        Channel channel = Amqp.getCurrentChannel();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                //TODO need to be implemented
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> json = mapper.readValue(body,Map.class);
                System.out.println(" [x] Received message : " + json);
            }
        };
        channel.basicConsume(Amqp.QUEUE_MODEL, true, consumer);

    }
}
