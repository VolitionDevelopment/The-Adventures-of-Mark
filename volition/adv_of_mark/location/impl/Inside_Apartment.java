package volition.adv_of_mark.location.impl;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.util.FileManager;


/**
 * Created by mccloskeybr on 3/2/16.
 */
public class Inside_Apartment extends Location {

    public Inside_Apartment(int x, int y) {
        super("Apartment Room", x, y);
    }

    @Override
    public void loadMap() {
        setTilemap(FileManager.getInstance().loadMapFromText("/volition/adv_of_mark/assets/maps/test_apartment.txt"));
    }
}
