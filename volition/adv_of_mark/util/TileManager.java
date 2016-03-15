package volition.adv_of_mark.util;


import volition.adv_of_mark.location.tile.Tile;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by mccloskeybr on 3/15/16.
 */
public class TileManager {

    public static HashMap<Integer, TileProperty> properties;

    /**
     * 0 = empty
     * 1 = wood floor
     * 2 = wood wall
     * 3 = brick floor
     * 4 = brick wall
     * 5 = grass floor
     * 6 = grass wall (hedge)
     * 7 = street floor
     */
    private static void loadProperties(){

        ImageManager im = ImageManager.getInstance();

        properties = new HashMap<>();

        properties.put(0, null);

        properties.put(1, new TileProperty(false, im.loadImage("/volition/adv_of_mark/assets/image/tiles/woodfloor.png")));
        properties.put(2, new TileProperty(true, im.loadImage("/volition/adv_of_mark/assets/image/tiles/woodwall.png")));

        properties.put(3, new TileProperty(false, im.loadImage("/volition/adv_of_mark/assets/image/tiles/brickfloor.png")));
        properties.put(4, new TileProperty(true, im.loadImage("/volition/adv_of_mark/assets/image/tiles/brickwall.png")));

        properties.put(5, new TileProperty(false, im.loadImage("/volition/adv_of_mark/assets/image/tiles/grassfloor.png")));
        properties.put(6, new TileProperty(true, im.loadImage("/volition/adv_of_mark/assets/image/tiles/grasswall.png")));

        properties.put(7, new TileProperty(false, im.loadImage("/volition/adv_of_mark/assets/image/tiles/streetfloor.png")));

    }

    public static void setProperties(Tile tile) {

        if (properties == null)
            loadProperties();

        properties.get(tile.getId()).setProperties(tile);

    }

}

class TileProperty {

    private boolean isSolid;
    private BufferedImage image;

    public TileProperty (boolean isSolid, BufferedImage image) {
        this.isSolid = isSolid;
        this.image = image;
    }

    public void setProperties(Tile tile){
        tile.setSolid(isSolid);
        tile.setImage(image);
    }

}
