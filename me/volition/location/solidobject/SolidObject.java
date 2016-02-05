package me.volition.location.solidobject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class SolidObject {

    private BufferedImage image;
    private int x, y;

    public SolidObject(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void render(Graphics g){
        g.drawImage(image, x, y, null);
    }
}
