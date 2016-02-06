package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class TexasCarpet extends PlaceableObject {
    public TexasCarpet(Tile[][] tileMap,int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/texascarpet.png"), tileMap, false, x, y);
    }
}
