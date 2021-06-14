package pt.deti.es.g31.dataprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pt.deti.es.g31.dataprocessor.models.AlertNotification;
import pt.deti.es.g31.dataprocessor.models.TempRepository;

import java.util.concurrent.CountDownLatch;

@Component
public class TestAlertConsumer {



    private String payload = null;
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "esp31-test-alert")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        payload = (String) consumerRecord.value();
        latch.countDown();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        pt.deti.es.g31.dataprocessor.models.AlertNotification not = new AlertNotification(System.currentTimeMillis() / 1000L, actualObj.get("alert_state").asText(), actualObj.get("description").asText());
        TempRepository.rep.add(not);
    }

    public String getPayload() {
        return payload;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}

