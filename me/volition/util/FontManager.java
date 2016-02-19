package me.volition.util;

import java.awt.*;
import java.io.InputStream;

/**
 * Created by mccloskeybr on 2/4/16.
 */
public class FontManager {

    private static Font sans;
    private static Font mono;

    public void registerFont(String path){
        InputStream is = getClass().getResourceAsStream(path);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try{
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Font getSans(int size){
        return new Font("Determination Sans", Font.PLAIN, size);
    }

    public static Font getMono(int size){
        return new Font("Determination Mono", Font.PLAIN, size);
    }

}
