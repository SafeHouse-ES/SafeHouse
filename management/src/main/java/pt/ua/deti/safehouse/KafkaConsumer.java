package pt.ua.deti.safehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pt.ua.deti.safehouse.data.Config;
import pt.ua.deti.safehouse.data.ConfigRepo;
import pt.ua.deti.safehouse.data.SensorDataTuple;

import java.util.List;

@Service
public class KafkaConsumer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ConfigRepo repo;

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private final String[] metrics = {"temperature", "luminosity", "movement"};

    private static final String TOPIC = "es31_management_commands";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "es31_sensordata", groupId = "group_id")
    public void consume(String message) {
        log.info(String.format("#### -> Consumed message -> %s", message));

        SensorDataTuple data = null;
        JsonNode json = null;
        try {
            json = mapper.readTree(message);
            data = new SensorDataTuple(json.get("timestamp").asLong(),json.get("id").asText(),json.get("temperature").asDouble(),json.get("luminosity").asDouble(),json.get("movement").asBoolean());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        for (String metric : metrics) {
            List<Config> cfgs = repo.findByRoomIDAndMetric(data.getId(),metric);
            for (Config cfg : cfgs) {
                if (cfg.issueCommand(data.getMetric(metric))) {
                    String command = "{\"id\":"+cfg.getRoomID()+", \"device\":"+cfg.getDeviceID()+", \"value\":"+cfg.getDeviceValue()+"}";
                    issueCommand(command);
                }
            }
        }
    }

    public void issueCommand(String message) {
        this.kafkaTemplate.send(TOPIC, message);
        log.info(String.format("Sent command %s", message));
    }

}
