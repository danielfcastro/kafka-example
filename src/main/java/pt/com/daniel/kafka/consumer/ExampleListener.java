package pt.com.daniel.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExampleListener {

    @KafkaListener(topics="example-cgi", groupId = "groupid")
    public void listener(String data){
        System.out.println("Listener received: "+data);
    }
}