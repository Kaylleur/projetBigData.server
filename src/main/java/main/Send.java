package main;
/**
 * Created by Thomas on 01/11/2015.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import models.Amqp;
import models.Task;
import resources.SummonerResource;

import java.io.File;

public class Send {

    /**
     * main.Send a "Hello World message"
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //Connection to the amqp server
        Amqp.connect(Amqp.QUEUE_TASK);
        Channel channel = Amqp.getCurrentChannel();

        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Integer[] ids = mapper.readValue(new File(classLoader.getResource("summonersIds.json").getFile()),Integer[].class);
        //Create a new task with parameter the class should be attacked and the method to invoke !
        for(Integer id : ids){
            Task task = new Task(SummonerResource.getSummoner(id));

            //Json mapper to convert to JSON
            String message = mapper.writeValueAsString(task);

            //publish the json to the queue and write it !
            channel.basicPublish("", Amqp.QUEUE_TASK, null, message.getBytes());
            System.out.println(" [x] Sent '" + task + " - " + message + "'");
        }


        System.exit(0);
    }
}
