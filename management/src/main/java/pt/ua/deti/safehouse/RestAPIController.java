package pt.ua.deti.safehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.ua.deti.safehouse.data.Config;
import pt.ua.deti.safehouse.data.ConfigRepo;

import java.util.List;

@RestController
public class RestAPIController {

    @Autowired
    private ConfigRepo repo;

    @GetMapping("/test")
    public String test() {
        return "HelloWorld!";
    }

    // --- GET ---

    @GetMapping("/single")
    public Config single(long id) {
        Config single = repo.findById(id);
        return single;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Config> all() {
        List<Config> lst = repo.findAll();
        return lst;
    }

    @GetMapping("/room")
    @ResponseBody
    public List<Config> room(String id) {
        List<Config> lst = repo.findByRoomID(id);
        return lst;
    }

    @GetMapping("/metric")
    @ResponseBody
    public List<Config> sensor(String metric) {
        List<Config> lst = repo.findByMetric(metric);
        return lst;
    }

    @GetMapping("/device")
    @ResponseBody
    public List<Config> device(String id) {
        List<Config> lst = repo.findByDeviceID(id);
        return lst;
    }

    // --- POST ---

    @PostMapping("/add")
    public void add(@RequestParam String room,@RequestParam String metric,@RequestParam Double sVal,@RequestParam Short order,@RequestParam String device,@RequestParam Double dVal) {
        System.out.printf("--> %s, %s, %f, %d, %s, %f\n", room, metric, sVal, order, device, dVal);
        Config cfg = new Config(room,metric,sVal,order,device,dVal);

        // TODO: change
        repo.save(cfg);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam long id) {
        Config cfg = repo.findById(id);
        repo.delete(cfg);
    }

}
