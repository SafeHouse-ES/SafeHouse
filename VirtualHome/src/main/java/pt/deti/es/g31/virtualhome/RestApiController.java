package pt.deti.es.g31.virtualhome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.deti.es.g31.virtualhome.Home;
import pt.deti.es.g31.virtualhome.HomeRepo;
import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private HomeRepo repo;

    @CrossOrigin
    @GetMapping("/test")
    public String test() {
        return "HelloWorld!";
    }

    // --- GET ---

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public List<Home> all() {
        List<Home> lst = repo.findAll();
        return lst;
    }

    @CrossOrigin
    @GetMapping("/room")
    @ResponseBody
    public Home room(String id) {
        Home lst = repo.findByRoomID(id);
        return lst;
    }
}