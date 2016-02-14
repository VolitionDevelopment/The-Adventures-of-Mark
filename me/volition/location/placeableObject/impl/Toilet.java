package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ItemEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Toilet extends PlaceableObject {
    public Toilet(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/toilet.png"), tileMap, ItemEvent.NONE, true, x, y);
    }

    public Toilet(Tile[][] tileMap, ItemEvent event, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/toilet.png"), tileMap, event, true, x, y);
    }
}
