package pt.ua.deti.safehouse.data;

import javax.persistence.*;

@Entity
@Table(name = "p31_Configs")
public class Config {

    private static final short GT = 0;
    private static final short LT = 1;

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "ROOM_ID")
    private String roomID;

    @Column(name = "METRIC")
    private String metric;

    @Column(name = "SENSOR_VAL")
    private Double sensorValue;

    @Column(name = "ORDER")
    private Short order;

    @Column(name = "DEVICE_ID")
    private String deviceID;

    @Column(name = "DEVICE_VAL")
    private Double deviceValue;

    protected Config() {}

    public Config(String roomID, String metric, double sensorValue, short order, String deviceID, double deviceValue) {
        this.roomID = roomID;
        this.metric = metric;
        this.sensorValue = sensorValue;
        this.order = order;
        this.deviceID = deviceID;
        this.deviceValue = deviceValue;
    }

    public long getId() {
        return id;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getMetric() {
        return metric;
    }

    public double getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(double sensorValue) {
        this.sensorValue = sensorValue;
    }

    public short getOrder() {
        return order;
    }

    public void setOrder(short order) {
        this.order = order;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public double getDeviceValue() {
        return deviceValue;
    }

    public void setDeviceValue(double deviceValue) {
        this.deviceValue = deviceValue;
    }

    public boolean issueCommand(double inValue) {
        switch (order) {
            case GT: return inValue > sensorValue;
            case LT: return inValue < sensorValue;
        }
        return false;
    }
}
