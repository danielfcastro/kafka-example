package pt.com.daniel.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExampleTopicConfig {
    @Bean
    public NewTopic exampleTopic(){
        return TopicBuilder.name("example-cgi").build();
    }
}
