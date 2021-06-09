package pt.deti.es.g31.dataprocessor.pubsub;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class LogProducer {

    private static final Logger logger = LoggerFactory.getLogger(LogProducer.class);
    private static final String TOPIC = "es31_dataprocessor_logs";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("%s", message));
        this.kafkaTemplate.send(TOPIC, String.format("{\"message\":\"%s\"}", message));
    }
}
