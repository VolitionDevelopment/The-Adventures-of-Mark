package volition.adv_of_mark.location.impl;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.util.FileManager;


/**
 * Created by mccloskeybr on 3/2/16.
 */
public class ApartmentRoom extends Location {

    public ApartmentRoom() {
        super("Apartment Room");
    }

    @Override
    public void loadMap() {
        setTilemap(FileManager.getInstance().loadMapFromText("/volition/adv_of_mark/assets/maps/test.txt"));
    }
}
