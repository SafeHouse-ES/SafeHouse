
package pt.deti.es.g31.virtualhome;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorDataTuple {

    private String roomId;

    private String deviceId;

    private Double value;


    public SensorDataTuple(String roomId, String deviceId, Double value) {
        this.roomId = roomId;
        this.deviceId = deviceId;
        this.value = value;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Double getValue() {
        return value;
    }

}