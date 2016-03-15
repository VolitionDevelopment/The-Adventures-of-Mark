package me.lvgen.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class TileManager {

    public static final int DEFAULT_ID = 0;

    private static int tileRender_size = 32;

    private static TileManager instance;
    public static TileManager getInstance(){
        if (instance == null)
            instance = new TileManager();

        return instance;
    }

    private HashMap<Integer, BufferedImage> idMap;

    public TileManager(){

        idMap = new HashMap<>();

        BufferedImage superImage = ImageManager.getInstance().loadImage("/me/lvgen/tilesheet/topdown_super.png");

        int id = 0;
        int x = 0, y = 0;
        while (y < superImage.getHeight()) {

            idMap.put(id, superImage.getSubimage(x, y, 64, 64));
            id++;

            x += 64;
            if (x >= superImage.getWidth()) {
                x = 0;
                y += 64;
            }
        }

    }

    public static int getTileRender_size(){
        return tileRender_size;
    }

    public static void setTileRender_size(int i){
        tileRender_size = i;
    }

    public HashMap<Integer, BufferedImage> getIdMap(){
        return idMap;
    }

    public BufferedImage getImage(int id){
        return idMap.get(id);
    }

}
