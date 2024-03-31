package pt.com.daniel.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication(scanBasePackages = {"pt.com.daniel.kafka"})
public class ConsumerApp {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ConsumerApp(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics="example-cgi", groupId = "groupid")
    public void listener(String data){
        System.out.println("Listener received: "+data);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
        System.out.println("ConsumerApp is running");
    }
}
