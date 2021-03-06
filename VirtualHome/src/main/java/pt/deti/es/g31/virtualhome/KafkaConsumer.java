package pt.deti.es.g31.virtualhome;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pt.deti.es.g31.virtualhome.*;
import java.util.List;

@Service
public class KafkaConsumer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private HomeRepo repo;

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "es31_management_commands", groupId = "group_id")
    public void consume(String message) {
        log.info(String.format("#### -> Consumed message -> %s", message));

        SensorDataTuple data = null;
        JsonNode json = null;
        try {
            json = mapper.readTree(message);
            data = new SensorDataTuple(json.get("id").asText(),json.get("device").asText(),json.get("value").asDouble());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        Home home = repo.findByRoomIDAndSensors(data.getRoomId(), data.getDeviceId());
        if (home == null){
            if(data.getValue() != 0) {
                home = new Home(data.getRoomId(), data.getDeviceId(), data.getValue());
                repo.save(home);
            }
        } else{
            if (data.getValue() == 0){
                repo.delete(home);
            } else {
                repo.delete(home);
                home = new Home(data.getRoomId(), data.getDeviceId(), data.getValue());
                repo.save(home);
            }
        }
    }

}