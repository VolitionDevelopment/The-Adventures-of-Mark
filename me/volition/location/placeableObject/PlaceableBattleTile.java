package me.volition.location.placeableObject;

import me.volition.entity.Entity;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/6/2016.
 */
public class PlaceableBattleTile extends PlaceableObject {

    public PlaceableBattleTile(Tile[][] tileMap, ArrayList<Entity> entities, double x, double y) {
        super(tileMap, entities, x, y);
    }
}
