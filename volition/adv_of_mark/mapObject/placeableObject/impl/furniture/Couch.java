package volition.adv_of_mark.mapObject.placeableObject.impl.furniture;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/8/16.
 */
public class Couch extends PlaceableObject {

    private static BufferedImage image;

    public Couch(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Couch", "I once gave birth on this beauty.", true, x, y, Tile.TILE_SIZE, 3 * Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/furniture/couch.png");

        return image;
    }

}
