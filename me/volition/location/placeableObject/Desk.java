package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class Desk extends PlaceableObject {
    public Desk(Tile[][] tileMap, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/desk.png"), tileMap, true, x, y);
    }
}
