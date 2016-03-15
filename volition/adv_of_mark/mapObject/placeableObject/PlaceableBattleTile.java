package volition.adv_of_mark.mapObject.placeableObject;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.location.tile.Tile;

import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/6/2016.
 */
public class PlaceableBattleTile extends PlaceableObject {

    public PlaceableBattleTile(Tile[][] tileMap, ArrayList<Entity> entities, double x, double y) {
        super(tileMap, entities, x, y);
    }
}
