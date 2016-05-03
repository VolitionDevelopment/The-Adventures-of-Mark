package volition.adv_of_mark.location.impl;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.util.FileManager;

/**
 * Created by mccloskeybr on 5/2/16.
 */
public class Forest extends Location {
    public Forest(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadMap() {
        setTilemap(FileManager.getInstance().loadTileMapFromText("/volition/adv_of_mark/assets/maps/location/forest.txt"));
    }
}
