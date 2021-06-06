package pt.deti.es.g31.dataprocessor.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pt.deti.es.g31.dataprocessor.models.AlertNotification;
import pt.deti.es.g31.dataprocessor.models.TempRepository;

import java.io.IOException;

@Service
public class AlertConsumer {

    @KafkaListener(topics = "es31_alerts", groupId = "group_id01")
    public void consume(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        AlertNotification not = new AlertNotification(System.currentTimeMillis() / 1000L, actualObj.get("alert_state").asText(), actualObj.get("description").asText());
        TempRepository.rep.add(not);
    }
}
