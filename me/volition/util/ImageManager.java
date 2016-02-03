package me.volition.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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
}
