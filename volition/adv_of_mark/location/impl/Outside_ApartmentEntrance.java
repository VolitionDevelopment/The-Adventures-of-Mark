package volition.adv_of_mark.location.impl;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.util.FileManager;

/**
 * Created by mccloskeybr on 4/27/16.
 */
public class Outside_ApartmentEntrance extends Location {

    public Outside_ApartmentEntrance(int x, int y) {
        super("Apartment Entrance", x, y);
    }

    @Override
    public void loadMap() {
        setTilemap(FileManager.getInstance().loadMapFromText("/volition/adv_of_mark/assets/maps/test_house.txt"));
    }

}
