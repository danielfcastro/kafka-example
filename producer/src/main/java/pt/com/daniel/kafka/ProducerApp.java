package pt.com.daniel.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication(scanBasePackages = {"pt.com.daniel.kafka"})
public class ProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
        System.out.println("ProducerApp is running");
    }
    //@Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
        return args-> {
            for(int i=1; i < 100; i++){
                kafkaTemplate.send("example-cgi", "Hello World :) " + i);
            }
        };
    }
}
