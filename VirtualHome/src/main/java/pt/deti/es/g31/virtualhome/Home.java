package pt.deti.es.g31.virtualhome;

import javax.persistence.*;

@Entity
@Table(name = "p31_Home")
public class Home {

    @Column(name = "ROOM_ID")
    @Id
    private String roomID;

    @Column(name = "SENSORS")
    private String sensors;


    protected Home() {}

    public Home(String roomID, String sensors) {
        this.roomID = roomID;
        this.sensors = sensors;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getSensors() {
        return sensors;
    }

    public boolean addSensors(String sensor) {
        if (sensors.contains(sensor)) return false;
        sensors += " " + sensor;
        sensors.replaceAll("\\s+", " ");
        return true;
    }

    public boolean removeSensors(String sensor) {
        if (!sensors.contains(sensor)) return false;
        sensors.replace(sensor, "");
        sensors.replaceAll("\\s+", " ");
        return true;
    }

    public boolean issueCommand(String sensor) {
        return (sensors.contains(sensor));
    }

}