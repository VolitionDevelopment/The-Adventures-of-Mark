package volition.lvgen.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_ObjectManager {

    public static final int DEFAULT_ID = 0;

    private static double tileRenderScale = 0.5;

    private static LvGen_ObjectManager instance;
    public static LvGen_ObjectManager getInstance(){
        if (instance == null)
            instance = new LvGen_ObjectManager();

        return instance;
    }

    private HashMap<Integer, BufferedImage> idMap;
    private HashMap<Integer, LvGen_ObjectProperty> objectMap;

    public LvGen_ObjectManager(){

        // load tiles

        BufferedImage tileSuperImage = LvGen_ImageManager.getInstance().loadImage("/volition/lvgen/tilesheet/topdown_super.png");

        idMap = new HashMap<>();

        int id = 0;
        int tile_x = 0, tile_y = 0;
        while (tile_y < tileSuperImage.getHeight()) {

            idMap.put(id, tileSuperImage.getSubimage(tile_x, tile_y, 64, 64));
            id++;

            tile_x += 64;
            if (tile_x >= tileSuperImage.getWidth()) {
                tile_x = 0;
                tile_y += 64;
            }
        }

        //load placeable objects

        BufferedImage objectIcon_SuperImage = LvGen_ImageManager.getInstance().loadImage("/volition/lvgen/tilesheet/objectIcon_super.png");
        BufferedImage objectMap_SuperImage = LvGen_ImageManager.getInstance().loadImage("/volition/lvgen/tilesheet/object_super.png");

        objectMap = new HashMap<>();

        id = 50;
        tile_x = tile_y = 0;
        int obj_x = 0, obj_y = 0; //loc of image to be displayed once obj has been placed

        while (tile_y < objectIcon_SuperImage.getHeight()) {

            int width = 0;
            while (obj_x + width != objectMap_SuperImage.getWidth() && objectMap_SuperImage.getRGB(obj_x + width, obj_y) == -1)
                width += 64;

            LvGen_ObjectProperty op = new LvGen_ObjectProperty(
                    objectIcon_SuperImage.getSubimage(tile_x, tile_y, 64, 64),
                    objectMap_SuperImage.getSubimage(obj_x, tile_y, width, 64));

            objectMap.put(id, op);
            id++;

            obj_y += 64;
            tile_x += 64;
            if (tile_x >= objectIcon_SuperImage.getWidth()) {
                tile_x = 0;
                tile_y += 64;
            }

        }

    }

    public static double getTileRenderScale(){
        return tileRenderScale;
    }

    public static void setTileRenderScale(int i){
        tileRenderScale = i;
    }

    public HashMap<Integer, BufferedImage> getIdMap(){
        return idMap;
    }

    public BufferedImage getTileIconImage(int id){
        return idMap.get(id);
    }

    public BufferedImage getObjIconImage(int id) {
        return objectMap.get(id).getIconImage();
    }

    public BufferedImage getObjMapImage(int id) {
        return objectMap.get(id).getMapImage();
    }

    public HashMap<Integer, LvGen_ObjectProperty> getObjectMap(){
        return objectMap;
    }

}
