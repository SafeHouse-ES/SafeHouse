package pt.deti.es.g31.dataprocessor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.influxdb.annotation.Column;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertNotification {

    private Long timestamp;

    private String alert_state;

    private String description;

    public AlertNotification(Long t, String a, String d){
        timestamp = t;
        alert_state = a;
        description = d;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("alert_state")
    public String getAlertState() {
        return alert_state;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public String toString(){
        return String.format("alert_state : %s, description : %s", alert_state, description);
    }

}
