package volition.adv_of_mark.mapObject.placeableObject.impl.furniture;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Wardrobe extends PlaceableObject {

    private static BufferedImage image;

    public Wardrobe(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Wardrobe", "I store my underwear here.", true, x, y, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/furniture/wardrobe.png");

        return image;
    }
}
