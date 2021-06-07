package pt.ua.deti.safehouse.data;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DBAccess {

    private static DBAccess db = new DBAccess();

    @Autowired
    private ConfigRepo repo;

    // TODO: Temporary db, remove
    public List<Config> tmp;

    private DBAccess() {
        tmp = new ArrayList<>();
    }

    public static ConfigRepo db() {
        return db.repo;
    }

    public static List<Config> tmp() {
        return db.tmp;
    }

}
