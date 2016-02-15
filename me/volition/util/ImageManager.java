package me.volition.util;

import me.volition.location.Location;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class ImageManager {

    private static ImageManager im;

    public static ImageManager getInstance(){
        if (im == null)
            im = new ImageManager();

        return im;
    }

    public BufferedImage loadImage(String path){
        try{
            return ImageIO.read(getClass().getResource(path));
        } catch (Exception ignored){}
        return null;
    }

    /**
     * has to have 2 separate 2d for loops because need to set up background first
     */
    //tiles should be wall, floor
    public static Tile[][] loadMapFromImage(Location location, BufferedImage map, ArrayList<Class<? extends Tile>> tiles, ArrayList<Class<? extends PlaceableObject>> objects){
        Tile[][] tileMap = new Tile[map.getHeight()][map.getWidth()];

        try {
            //tiles
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
                    int rgb = map.getRGB(j, i);

                    //BLACK
                    if (rgb == -16777216)
                        tileMap[i][j] = (Tile) tiles.get(0).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                    else
                        tileMap[i][j] = (Tile) tiles.get(1).getConstructors()[0].newInstance(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);

                }
            }

            //placeable objects
            for (int i = 0; i < tileMap.length; i++){
                for (int j = 0; j < tileMap[i].length; j++){
                    int rgb = map.getRGB(j, i);

                    //RED
                    if (rgb == -1237980)
                        location.addPlaceableObject((PlaceableObject) objects.get(0).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //DARK RED
                    else if (rgb == -9761005)
                        location.addPlaceableObject((PlaceableObject) objects.get(1).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //ORANGE
                    else if (rgb == -32985)
                        location.addPlaceableObject((PlaceableObject) objects.get(2).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //DARK ORANGE
                    else if (rgb == -6796264)
                        location.addPlaceableObject((PlaceableObject) objects.get(3).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //YELLOW
                    else if (rgb == -3584)
                        location.addPlaceableObject((PlaceableObject) objects.get(4).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //DARK YELLOW
                    else if (rgb == -8094199)
                        location.addPlaceableObject((PlaceableObject) objects.get(5).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //GREEN
                    else if (rgb == -14503604)
                        location.addPlaceableObject((PlaceableObject) objects.get(6).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //DARK GREEN
                    else if (rgb == -16164063)
                        location.addPlaceableObject((PlaceableObject) objects.get(7).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //BLUE
                    else if (rgb == -16735512)
                        location.addPlaceableObject((PlaceableObject) objects.get(8).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //DARK BLUE
                    else if (rgb == -16569025)
                        location.addPlaceableObject((PlaceableObject) objects.get(9).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //INDIGO
                    else if (rgb == -12629812)
                        location.addPlaceableObject((PlaceableObject) objects.get(10).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //DARK INDIGO
                    else if (rgb == -16578478)
                        location.addPlaceableObject((PlaceableObject) objects.get(11).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //VIOLET
                    else if (rgb == -6075996)
                        location.addPlaceableObject((PlaceableObject) objects.get(12).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                    //DARK VIOLET
                    else if (rgb == -11728306)
                        location.addPlaceableObject((PlaceableObject) objects.get(13).getConstructors()[0].newInstance(tileMap, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
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
