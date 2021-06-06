package pt.deti.es.g31.dataprocessor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class TestAlertConsumer {



    private String payload = null;
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${test.topic}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        payload = (String) consumerRecord.value();
        latch.countDown();
    }

    public String getPayload() {
        return payload;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
