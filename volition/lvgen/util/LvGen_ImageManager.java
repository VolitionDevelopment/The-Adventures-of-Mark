package volition.lvgen.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_ImageManager {

    private static LvGen_ImageManager instance;

    public static LvGen_ImageManager getInstance(){

        if (instance == null)
            instance = new LvGen_ImageManager();

        return instance;

    }

    public BufferedImage loadImage(String path){

        try{
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException e){ e.printStackTrace(); }

        return null;

    }

}
