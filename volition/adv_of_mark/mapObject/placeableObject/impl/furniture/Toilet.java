package volition.adv_of_mark.mapObject.placeableObject.impl.furniture;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Toilet extends PlaceableObject {

    private static BufferedImage image;

    public Toilet(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "The Porcelain Throne", "It's positioned in such a way that I can stare my lover in the eyes while I take a dump.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/furniture/toilet.png");

        return image;
    }
}
