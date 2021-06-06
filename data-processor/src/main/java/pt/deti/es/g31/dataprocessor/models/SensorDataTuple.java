package pt.deti.es.g31.dataprocessor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import java.time.Instant;


@JsonIgnoreProperties(ignoreUnknown = true)
@Measurement(name = "sensorMeasure")
public class SensorDataTuple {

    @Column(name = "time")
    private Instant timestamp;

    @Column(name = "sensorId")
    private String sensorId;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "luminosity")
    private Double luminosity;

    @Column(name = "movement")
    private Boolean movement;

    /*public SensorDataTuple(Instant timestamp, String id, Double temperature, Double luminosity, Boolean movement){
        this.timestamp = timestamp;
        this.id = id;
        this.temperature = temperature;
        this.luminosity = luminosity;
        this.movement = movement;
    }*/

    @JsonProperty("timestamp")
    public Instant getTimestamp() {
        return timestamp;
    }

    @JsonProperty("id")
    public String getId() {
        return sensorId;
    }

    @JsonProperty("temperature")
    public Double getTemperature() {
        return temperature;
    }

    @JsonProperty("luminosity")
    public Double getLuminosity() {
        return luminosity;
    }

    @JsonProperty("movement")
    public Boolean getMovement() {
        return movement;
    }

}