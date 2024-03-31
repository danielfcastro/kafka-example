package pt.com.daniel.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.com.daniel.kafka.ProducerApp;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class ExampleProducer {
    private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ExampleProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        String topicName = "example-cgi";
        kafkaTemplate.send(topicName, message);
    }
}
