package me.volition.util;

import me.volition.location.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class ImageManager {

    public BufferedImage loadImage(String path){
        try{
            return ImageIO.read(getClass().getResource(path));
        } catch (Exception ignored){}
        return null;
    }

    public static Tile[][] loadMapFromImage(BufferedImage map){
        Tile[][] tileMap = new Tile[map.getHeight()][map.getWidth()];

        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++) {
                int rgb = map.getRGB(j, i);
                if (rgb == 0){

                } else if (rgb == 1) {
                    
                }
            }
        }

        return tileMap;
    }
}
