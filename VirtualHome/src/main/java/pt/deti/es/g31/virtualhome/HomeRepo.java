package pt.deti.es.g31.virtualhome;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HomeRepo extends JpaRepository<Home,Long> {

    Home findByRoomID(String roomID);
}