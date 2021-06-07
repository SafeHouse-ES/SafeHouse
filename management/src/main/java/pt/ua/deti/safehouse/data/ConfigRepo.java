package pt.ua.deti.safehouse.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepo extends JpaRepository<Config,Long> {

    Config findById(long id);

    List<Config> findByRoomID(String roomID);

    List<Config> findByMetric(String metric);

    List<Config> findByDeviceID(String deviceID);

    List<Config> findByRoomIDAndMetric(String roomID, String metric);
}
