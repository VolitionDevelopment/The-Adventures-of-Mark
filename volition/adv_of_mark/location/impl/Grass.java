package volition.adv_of_mark.location.impl;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.util.FileManager;

/**
 * Created by mccloskeybr on 4/27/16.
 */
public class Grass extends Location{

    public Grass(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadMap() {
        setTilemap(FileManager.getInstance().loadTileMapFromText("/volition/adv_of_mark/assets/maps/location/grass.txt"));
    }
}
