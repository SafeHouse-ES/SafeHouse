package pt.deti.es.g31.virtualhome;

import java.io.Serializable;

public class RoomDevice implements Serializable{

    private static final long serialVersionUID = 1L;

    
    private String roomID;
    
    private String sensors;

    
    public RoomDevice(){
        
    }

    public RoomDevice(String roomID, String sensors) {
        this.roomID = roomID;
        this.sensors = sensors;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getSensors() {
        return sensors;
    }

}