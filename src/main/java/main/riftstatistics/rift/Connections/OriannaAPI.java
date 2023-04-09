package main.riftstatistics.rift.Connections;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;

public class OriannaAPI {

    private static OriannaAPI instance;
    private static final String REGION = Region.EUROPE_WEST.name();
    private static final int MAX_CACHED_GAMES = 1000;
    private Orianna orianna;

    private OriannaAPI() {
    }

    public static OriannaAPI getInstance() {
        if (instance == null) {
            instance = new OriannaAPI();
        }
        return instance;
    }


}
