package main;
/**
 * Created by Thomas on 01/11/2015.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import models.Amqpa;
import models.Task;
import resources.SummonerResource;

public class Send {

    /**
     * main.Send a "Hello World message"
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //Connection to the amqp server
        Channel channel = Amqpa.connect();

        //Create a new task with parameter the class should be attacked and the method to invoke !

//        Task task = new Task<Summoner>(Summoner.class,"getSummonerWithId",new Class[]{int.class},new Object[]{1});
        Task task = new Task(SummonerResource.getSummoner(19838593));

        //Json mapper to convert to JSON
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(task);

        //publish the json to the queue and write it !
        channel.basicPublish("", Amqpa.QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + task + " - " + message + "'");
        System.exit(0);
    }
}
