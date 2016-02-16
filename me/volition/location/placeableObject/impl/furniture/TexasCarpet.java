package me.volition.location.placeableObject.impl.furniture;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class TexasCarpet extends PlaceableObject {
    public TexasCarpet(Tile[][] tileMap, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/texascarpet.png"), tileMap, ObjectEvent.NONE, "Carpet of Texas", "Yee-haw!", false, x, y);
    }
}
