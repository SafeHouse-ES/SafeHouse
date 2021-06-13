package pt.deti.es.g31.dataprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pt.deti.es.g31.dataprocessor.models.AlertNotification;
import pt.deti.es.g31.dataprocessor.models.TempRepository;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepDataprocessorApplicationTests extends DataprocessorApplicationTests{

    HttpHeaders headers;
    HttpEntity entity;

    @Given("the kitchen luminosity sensor generates an alert")
    public void the_kitchen_luminosity_sensor_generates_an_alert() throws Throwable{
        producer.send(topic, "{\"alert_state\" : \"alerting\", \"description\" : \"Kitchen Dark Alert - Too dark in Kitchen\"}");
    }

    @When("the alert is consumed")
    public void the_alert_is_consumed() throws Throwable{
        //wait for the application to produce the response
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }


    @Then("the type of alert should be available in the /alerts endpoint")
    public void the_alert_should_be_available_in_the_endpoint() throws Throwable{
        headers = new HttpHeaders();
        entity = new HttpEntity(headers);

        ResponseEntity<String> result = restTemplate.exchange(server+""+randomServerPort+""+"/alerts?seg=60", HttpMethod.GET, entity, String.class);

        //Parse api response in order to validate the fields
        int size_a = 0;
        JSONObject json = null;
        try {
            JSONArray json_a = (JSONArray)new JSONParser().parse(result.getBody());
            json = (JSONObject) json_a.get(0);
            size_a = json_a.size();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String state =(String)json.get("alert_state");
        assertEquals(1, size_a);
        assertEquals("alerting", state);
        assertEquals(200, result.getStatusCodeValue());
    }

    @When("users want to get the data about the metrics")
    public void users_want_to_get_the_data_about_the_metrics() throws Throwable{
        headers = new HttpHeaders();
        entity = new HttpEntity(headers);
    }

    @Then("the list with the metrics keys is returned")
    public void the_list_with_the_metrics_keys_is_returned() throws Throwable{
        ResponseEntity<String> result = restTemplate.exchange(server+""+randomServerPort+""+"/metrics", HttpMethod.GET, entity, String.class);

        //Parse api response in order to validate the fields
        int size_a = 0;
        JSONObject json = null;
        try {
            JSONArray json_a = (JSONArray)new JSONParser().parse(result.getBody());
            size_a = json_a.size();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(3, size_a);
    }

    @And("the api returns a success status")
    public void the_api_returns_a_success_status() throws Throwable{
        ResponseEntity<String> result = restTemplate.exchange(server+""+randomServerPort+""+"/metrics", HttpMethod.GET, entity, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Given("the users does not have the sensor id that they need")
    public void the_users_does_not_have_the_sensor_id_that_they_need() throws Throwable{
        //For Readability only
    }

    @When("the user requests sensors data")
    public void the_user_requests_sensors_data() throws Throwable{
        headers = new HttpHeaders();
        entity = new HttpEntity(headers);
    }

    @Then("the list with the measurements from all the sensors is returned")
    public void the_list_with_the_measurements_from_all_the_sensors_is_returned() throws Throwable{
        ResponseEntity<String> result = restTemplate.exchange(server+""+randomServerPort+""+"/all", HttpMethod.GET, entity, String.class);

        //Parse api response in order to validate the fields
        int size_a = 0;
        JSONObject json = null;
        try {
            JSONArray json_a = (JSONArray)new JSONParser().parse(result.getBody());
            size_a = json_a.size();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(size_a  >  0);
    }

    @And("the api returns a success status 200")
    public void the_api_returns_a_success_status_200() throws Throwable{
        ResponseEntity<String> result = restTemplate.exchange(server+""+randomServerPort+""+"/all", HttpMethod.GET, entity, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Given("the users have the sensor id that they want")
    public void the_users_have_the_sensor_id_that_they_want() throws Throwable{
        //For Readability only
    }

    @When("the user requests kitchen sensors data")
    public void the_user_requests_kitchen_sensors_data() throws Throwable{
        headers = new HttpHeaders();
        entity = new HttpEntity(headers);
    }

    @Then("the list with the measurements from the sensor specified is returned")
    public void the_list_with_the_measurements_from_the_sensor_specified_is_returned() throws Throwable{
        ResponseEntity<String> result = restTemplate.exchange(server+""+randomServerPort+""+"/sensor/all?sensorId=Kitchen", HttpMethod.GET, entity, String.class);

        //Parse api response in order to validate the fields
        int size_a = 0;
        JSONObject json = null;
        JSONArray json_a = null;
        try {
            json_a = (JSONArray)new JSONParser().parse(result.getBody());
            size_a = json_a.size();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Test assert conditions
        Assert.assertTrue(size_a  >  0);
        for(int i = 0; i<size_a; i++){
            json = (JSONObject) json_a.get(i);
            String id = (String) json.get("id");
            assertEquals("Kitchen", id);
        }

    }

    @And("the api returns success status 200")
    public void the_api_returns_success_status_200() throws Throwable{
        ResponseEntity<String> result = restTemplate.exchange(server+""+randomServerPort+""+"/sensor/all?sensorId=Kitchen", HttpMethod.GET, entity, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }




}

