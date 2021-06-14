package pt.ua.deti.safehouse;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class TestCommandConsumer {

    private String payload = "{\"id\":\"test_room\",\"device\":\"test_dev\",\"value\":10}";
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "es31_management_commands")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        latch.countDown();
    }

    public String getPayload() {
        return payload;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
