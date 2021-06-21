package pt.ua.deti.safehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import pt.ua.deti.safehouse.data.Config;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepMangementApiApplicationTests extends ManagementApiApplicationTests {

    private final String senId = "test_room";
    private final String devId = "test_dev";
    private final int newVal = 10;

    @Given("there is a configuration for when temperature in Room1 is higher than forty")
    public void there_is_a_configuration() throws Throwable {
        Config cfg = new Config(senId,"temperature",0,(short) 0,devId,newVal);
        repo.save(cfg);
    }

    @When("temperature in Room1 is higher than forty")
    public void the_sensor_value_violates_a_condition() throws Throwable {
        sensorProducer.send(topic,"{\"id\":\"test_room\",\"temperature\":10.0,\"luminosity\":0.0,\"movement\":true,\"timestamp\":1623622582377}");
    }

    @Then("an automatic command is sent to lower the temperature in Room1 to 25")
    public void an_automatic_command_is_sent() throws Throwable {
        cmdConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        String payload = cmdConsumer.getPayload();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = mapper.readTree(payload);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(json);

        Assert.assertEquals(senId,json.get("id").asText());
        Assert.assertEquals(devId,json.get("device").asText());
        Assert.assertEquals(newVal,json.get("value").asInt());
    }

    @After
    public void cleanup() throws Throwable {
        List<Config> cfgs = repo.findByRoomID("test_room");
        repo.deleteAll(cfgs);
    }


}
