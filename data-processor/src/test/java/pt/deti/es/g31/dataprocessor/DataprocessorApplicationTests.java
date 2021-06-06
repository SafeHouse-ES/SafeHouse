package pt.deti.es.g31.dataprocessor;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

import pt.deti.es.g31.dataprocessor.controllers.DataprocessorApplication;

@CucumberContextConfiguration
@SpringBootTest (classes = DataprocessorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class DataprocessorApplicationTests {

	@Autowired
	protected TestRestTemplate restTemplate;

	@Autowired
	protected TestAlertConsumer consumer;

	@Autowired
	protected TestAlertProducer producer;

	@Value("${test.topic}")
	protected String topic;

	@LocalServerPort
	protected int randomServerPort;

	protected String server="http://localhost:";





}
