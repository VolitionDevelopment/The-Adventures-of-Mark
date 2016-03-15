package volition.adv_of_mark.mapObject.placeableObject.impl.furniture;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class Desk extends PlaceableObject {

    private static BufferedImage image;

    public Desk(int x, int y) {
        super(loadImage(), ObjectEvent.NONE, "A Desk", "9001 nights spent bullshitting essays.", true, x, y, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/furniture/desk.png");

        return image;
    }
}
