package volition.adv_of_mark.util;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.location.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    //requires tilemap to have already been placed
    public static BufferedImage makeImageFromMap(Tile[][] tilemap){

        BufferedImage image =
                new BufferedImage(
                        tilemap.length * Tile.TILE_SIZE / 2 + tilemap[0].length * Tile.TILE_SIZE / 2,
                        tilemap.length * Tile.TILE_SIZE / 4 + tilemap[0].length * Tile.TILE_SIZE / 4 + Tile.TILE_SIZE,
                        BufferedImage.TYPE_INT_ARGB
                );
        Graphics g = image.createGraphics();

        for (int i = 0; i < tilemap.length; i++) {
            for (int j = 0; j < tilemap[i].length; j++) {

                g.drawImage(
                        tilemap[i][j].getImage(),
                        (tilemap.length * Tile.TILE_SIZE / 2) // centers the map
                                + (Tile.TILE_SIZE / 2) * j - (Tile.TILE_SIZE / 2) * i - Tile.TILE_SIZE / 2, //draws x
                        (Tile.TILE_SIZE / 4) * j + (Tile.TILE_SIZE / 4) * i + Tile.TILE_SIZE / 2, // draws y
                        null
                );

                // walls get placed one on top of the bottom one (so its actually a wall XD)
                if (tilemap[i][j].getId() % 2 == 0)
                    g.drawImage(
                            tilemap[i][j].getImage(),
                            (tilemap.length * Tile.TILE_SIZE / 2) // centers the map
                                    + (Tile.TILE_SIZE / 2) * j - (Tile.TILE_SIZE / 2) * i - Tile.TILE_SIZE / 2, //draws x
                            (Tile.TILE_SIZE / 4) * j + (Tile.TILE_SIZE / 4) * i, // draws y
                            null
                    );
            }
        }

        return image;

    }

    public static BufferedImage makeBackgroundImage(Location[][] surroundingLocations) {

        BufferedImage[][] surroundingLocationImages = new BufferedImage[surroundingLocations.length][surroundingLocations[0].length];

        int size = surroundingLocationImages.length;

        for (int i = 0; i < surroundingLocationImages.length; i++)
            for (int j = 0; j < surroundingLocationImages[i].length; j++)
                if (surroundingLocations[i][j] != null)
                    surroundingLocationImages[i][j] = surroundingLocations[i][j].getTileImage();

        BufferedImage bgImage = new BufferedImage(
                size * (15 * Tile.TILE_SIZE) / 2 + size * (15 * Tile.TILE_SIZE) / 2,
                size * (15 * Tile.TILE_SIZE) / 4 + size * (15 * Tile.TILE_SIZE) / 4 + (15 * Tile.TILE_SIZE) / 2,
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics g = bgImage.createGraphics();

        for (int i = 0; i < surroundingLocationImages.length; i++)
            for (int j = 0; j < surroundingLocationImages[i].length; j++)
                if (surroundingLocationImages[i][j] != null) {
                    g.drawImage(
                            surroundingLocationImages[i][j],
                            (size * (15 * Tile.TILE_SIZE) / 2) // centers the map
                                    + ((15 * Tile.TILE_SIZE) / 2) * j - ((15 * Tile.TILE_SIZE) / 2) * i - (15 * Tile.TILE_SIZE) / 2, //draws x
                            ((15 * Tile.TILE_SIZE) / 4) * j + ((15 * Tile.TILE_SIZE) / 4) * i + Tile.TILE_SIZE / 2, // draws y
                            null);
                }

        return bgImage;

    }

    public static void printRGB(BufferedImage image){
        for (int i = 0; i < image.getHeight(); i++)
            for (int j = 0; j < image.getWidth(); j++)
                System.out.println(image.getRGB(j, i));
    }

    public static void saveImage(BufferedImage image){
        try {
            ImageIO.write(image, "PNG", new File("image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printRGB(BufferedImage image, int x, int y){
        System.out.println(image.getRGB(x, y));
    }
}










