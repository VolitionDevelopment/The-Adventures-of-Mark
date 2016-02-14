package me.volition.util;

import me.volition.location.placeableObject.PlaceableObject;
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

    public static Tile[][] loadMapFromImage(BufferedImage map, ArrayList<Class<? extends Tile>> tiles, ArrayList<Class<? extends PlaceableObject>> objects){
        Tile[][] tileMap = new Tile[map.getHeight()][map.getWidth()];

        try {
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
                    int rgb = map.getRGB(j, i);
                    //RED
                    if (rgb == -1237980)
                        tileMap[i][j] = (Tile) tiles.get(0).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //ORANGE
                    else if (rgb == -32985)
                        tileMap[i][j] = (Tile) tiles.get(1).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //YELLOW
                    else if (rgb == -3584)
                        tileMap[i][j] = (Tile) tiles.get(2).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //GREEN
                    else if (rgb == -14503604)
                        tileMap[i][j] = (Tile) tiles.get(3).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //BLUE
                    else if (rgb == -16735512)
                        tileMap[i][j] = (Tile) tiles.get(4).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //INDIGO
                    else if (rgb == -12629812)
                        tileMap[i][j] = (Tile) tiles.get(5).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //VIOLET
                    else if (rgb == -6075996)
                        tileMap[i][j] = (Tile) tiles.get(6).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //BLACK
                    else if (rgb == -16777216)
                        tileMap[i][j] = (Tile) tiles.get(7).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    //WHITE
                    else if (rgb == -1)
                        tileMap[i][j] = (Tile) tiles.get(8).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return tileMap;
    }

    public static void printRGB(BufferedImage image){
        for (int i = 0; i < image.getHeight(); i++)
            for (int j = 0; j < image.getWidth(); j++)
                System.out.println(image.getRGB(j, i));
    }
}
