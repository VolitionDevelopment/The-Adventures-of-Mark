package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class Desk extends PlaceableObject {
    public Desk(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/desk.png"), ObjectEvent.NONE, "A Desk", "9001 nights spent bullshitting essays.", true, x, y);
    }
}
