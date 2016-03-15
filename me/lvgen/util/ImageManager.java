package me.lvgen.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class ImageManager {

    private static ImageManager instance;

    public static ImageManager getInstance(){

        if (instance == null)
            instance = new ImageManager();

        return instance;

    }

    public BufferedImage loadImage(String path){

        try{
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException e){ e.printStackTrace(); }

        return null;

    }

}
