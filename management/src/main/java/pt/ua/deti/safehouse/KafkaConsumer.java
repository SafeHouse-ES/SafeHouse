package pt.ua.deti.safehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pt.ua.deti.safehouse.data.Config;
import pt.ua.deti.safehouse.data.ConfigRepo;
import pt.ua.deti.safehouse.data.DBAccess;
import pt.ua.deti.safehouse.data.SensorDataTuple;

import java.util.List;

@Service
public class KafkaConsumer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ConfigRepo repo;

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private final String[] metrics = {"temperature", "luminosity", "movement"};

    @KafkaListener(topics = "es31_sensordata", groupId = "group_id")
    public void consume(String message) {
        log.info(String.format("#### -> Consumed message -> %s", message));

        SensorDataTuple data = null;
        try {
            data = mapper.readValue(message,SensorDataTuple.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        for (String metric : metrics) {
            List<Config> cfgs = repo.findByRoomIDAndMetric(data.getId(),metric);
            for (Config cfg : cfgs) {
                if (cfg.issueCommand(data.getMetric(metric))) {
                    System.out.println("Issue command");
                    // TODO: publish to kafka topic
                }
            }
        }
    }

}
