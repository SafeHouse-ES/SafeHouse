package pt.deti.es.g31.virtualhome;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(RoomDevice.class)
@Table(name = "p31_Home")
public class Home implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "ROOM_ID")
    @Id
    private String roomID;
    
    @Column(name = "SENSORS")
    @Id
    private String sensors;

    @Column(name = "VALUE")
    private double value;


    protected Home() {}

    public Home(String roomID, String sensors, double value) {
        this.roomID = roomID;
        this.sensors = sensors;
        this.value = value;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getSensors() {
        return sensors;
    }

    public double getValue () {
        return value;
    }

}