
package pt.ua.deti.safehouse.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorDataTuple {

    private long timestamp;

    private String sensorId;

    private Double temperature;

    private Double luminosity;

    private Boolean movement;

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return sensorId;
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

    public Double getMetric(String metric) {
        switch (metric) {
            case "temperature": return temperature;
            case "luminosity": return luminosity;
            case "movement": return movement?1.0:0.0;
        }
        return null;
    }

}