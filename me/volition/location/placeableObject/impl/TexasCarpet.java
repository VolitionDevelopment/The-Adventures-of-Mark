package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class TexasCarpet extends PlaceableObject {
    public TexasCarpet(Tile[][] tileMap, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/texascarpet.png"), tileMap, ObjectEvent.NONE, false, x, y);
    }

    public TexasCarpet(Tile[][] tileMap, ObjectEvent event, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/texascarpet.png"), tileMap, event, false, x, y);
    }
}
