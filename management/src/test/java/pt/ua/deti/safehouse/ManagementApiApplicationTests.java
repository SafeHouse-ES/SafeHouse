package pt.ua.deti.safehouse;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import pt.ua.deti.safehouse.data.ConfigRepo;

@CucumberContextConfiguration
@SpringBootTest(classes = ManagementApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ManagementApiApplicationTests {

    @Autowired
    protected TestSensorProducer sensorProducer;

    @Autowired
    protected TestCommandConsumer cmdConsumer;

    @Autowired
    protected ConfigRepo repo;

    protected String topic = "es31_sensordata";

    @LocalServerPort
    protected int randomServerPort;

    protected String server="http://localhost:";

}
