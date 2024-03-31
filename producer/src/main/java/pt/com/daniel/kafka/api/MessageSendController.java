package pt.com.daniel.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageSendController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @PostMapping
    public void publishMessage(@RequestBody MessageRequest messageRequest) {
        kafkaTemplate.send("example-cgi", messageRequest.getMessage());
    }
}
