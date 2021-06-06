package pt.deti.es.g31.dataprocessor.controllers;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.deti.es.g31.dataprocessor.models.AlertNotification;
import pt.deti.es.g31.dataprocessor.models.SensorDataTuple;
import pt.deti.es.g31.dataprocessor.models.TempRepository;
import pt.deti.es.g31.dataprocessor.pubsub.LogProducer;



@RestController
public class RestAPIController {

    private final LogProducer producer;

    @Autowired
    RestAPIController(LogProducer producer) {
        this.producer = producer;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @CrossOrigin
    @GetMapping("/all")
    List<SensorDataTuple> allSensorValues() {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.160.18:8086", ".", "");

        Query queryObject = new Query("Select * From sensorMeasure", "es31_sensordata");
        QueryResult queryResult = influxDB.query(queryObject);

        // Map it
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<SensorDataTuple> l = resultMapper.toPOJO(queryResult, SensorDataTuple.class);
        System.out.printf("%d tamanho\n", l.size());

        String message = "INFO -- Endpoint /all 200";
        this.producer.sendMessage(message);
        return l;
    }
    
    @CrossOrigin	
    @RequestMapping(path = "/all/interval", method = RequestMethod.GET)
    List<SensorDataTuple> allSensorValuesInterval(@RequestParam String inf, @RequestParam String sup) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.160.18:8086", ".", "");

        Query queryObject = new Query("Select * From sensorMeasure Where time >= '"+inf+"' AND time <= '"+sup+"'", "es31_sensordata");
        QueryResult queryResult = influxDB.query(queryObject);

        // Map it
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<SensorDataTuple> l = resultMapper.toPOJO(queryResult, SensorDataTuple.class);
        System.out.printf("%d tamanho\n", l.size());

        String message = "INFO -- Endpoint /all/interval 200";
        this.producer.sendMessage(message);
        return l;
    }
	
    @CrossOrigin
    @GetMapping("/sensor/all")
    List<SensorDataTuple> allValuesFromSensor(@RequestParam String sensorId) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.160.18:8086", ".", "");

        Query queryObject = new Query("Select * From sensorMeasure Where sensorId = '"+sensorId+"'", "es31_sensordata");
        QueryResult queryResult = influxDB.query(queryObject);

        // Map it
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<SensorDataTuple> l = resultMapper.toPOJO(queryResult, SensorDataTuple.class);
        System.out.printf("%d tamanho\n", l.size());

        String message = "INFO -- Endpoint /sensor/all 200";
        this.producer.sendMessage(message);
        return l;
    }

    @CrossOrigin
    @RequestMapping(path = "/sensor/interval", method = RequestMethod.GET)
    List<SensorDataTuple> allValuesFromSensorInterval(@RequestParam String sensorId, @RequestParam String inf, @RequestParam String sup) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.160.18:8086", ".", "");

        Query queryObject = new Query("Select * From sensorMeasure Where sensorId = '"+sensorId+"' AND time >= '"+inf+"' AND time <= '"+sup+"'", "es31_sensordata");
        QueryResult queryResult = influxDB.query(queryObject);

        // Map it
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<SensorDataTuple> l = resultMapper.toPOJO(queryResult, SensorDataTuple.class);
        System.out.printf("%d tamanho\n", l.size());

        String message = "INFO -- Endpoint /sensor/interval 200";
        this.producer.sendMessage(message);
        return l;
    }

    @CrossOrigin
    @GetMapping("/sensor/recent")
    SensorDataTuple mostRecentValueFromSensor(@RequestParam String sensorId) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.160.18:8086", ".", "");

        Query queryObject = new Query("Select * From sensorMeasure Where sensorId = '"+sensorId+"'", "es31_sensordata");
        QueryResult queryResult = influxDB.query(queryObject);

        // Map it
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<SensorDataTuple> l = resultMapper.toPOJO(queryResult, SensorDataTuple.class);
        System.out.printf("%d tamanho\n", l.size());

        String message = "INFO -- Endpoint /sensor/recent 200";
        this.producer.sendMessage(message);
        return l.get(l.size()-1);
    }

    @CrossOrigin
    @GetMapping("/metrics")
    List<String> allMetrics() {
        List<String> l = new ArrayList<>();
        l.add("temperature");
        l.add("luminosity");
        l.add("movement");
        System.out.printf("%d tamanho\n", l.size());

        String message = "INFO -- Endpoint /metrics 200";
        this.producer.sendMessage(message);
        return l;
    }

    @CrossOrigin
    @GetMapping("/sensors")
    List<String> allSensors() {
        List<String> l = new ArrayList<>();
        l.add("Kitchen");
        l.add("LivingRoom");
        l.add("Room1");
        l.add("Room2");
        System.out.printf("%d tamanho\n", l.size());

        String message = "INFO -- Endpoint /sensors 200";
        this.producer.sendMessage(message);
        return l;
    }

    @CrossOrigin
    @GetMapping("/alerts")
    List<AlertNotification> allAlerts(@RequestParam Long seg) {

        String message = "INFO -- Endpoint /alerts 200";
        Long now = System.currentTimeMillis() / 1000L;
        List<AlertNotification> list = TempRepository.rep
                .stream()
                .filter(a -> a.getTimestamp() > now-seg)
                .collect(Collectors.toList());

        this.producer.sendMessage(message);
        return list;
    }

}
