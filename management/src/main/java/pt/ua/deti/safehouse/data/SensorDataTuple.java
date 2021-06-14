
package pt.ua.deti.safehouse.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorDataTuple {

    private long timestamp;

    private String sensorId;

    private Double temperature;

    private Double luminosity;

    private Boolean movement;

    public SensorDataTuple(long timestamp, String sensorId, Double temperature, Double luminosity, Boolean movement) {
        this.timestamp = timestamp;
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.luminosity = luminosity;
        this.movement = movement;
    }

    public String getTimestamp() {
        return sensorId;
    }

    public String getId() {
        return sensorId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getLuminosity() {
        return luminosity;
    }

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