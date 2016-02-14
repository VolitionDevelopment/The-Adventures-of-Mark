package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ItemEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class TexasCarpet extends PlaceableObject {
    public TexasCarpet(Tile[][] tileMap, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/texascarpet.png"), tileMap, ItemEvent.NONE, false, x, y);
    }

    public TexasCarpet(Tile[][] tileMap, ItemEvent event, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/texascarpet.png"), tileMap, event, false, x, y);
    }
}
