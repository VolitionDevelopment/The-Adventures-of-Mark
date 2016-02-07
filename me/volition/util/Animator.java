package me.volition.util;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class Animator {

    private BufferedImage[] images;
    private int currentImageIndex;
    private int currentTick, maxTick;

    public Animator(BufferedImage[] images){
        this.images = images;
        currentImageIndex = 0;

        maxTick = 1000000;
    }

    public void setCurrentImageIndex(int currentImageIndex){
        this.currentImageIndex = currentImageIndex;
    }

    public void reset(){
        currentImageIndex = 0;
        currentTick = 0;
    }

    public void update(){
        currentTick ++;
        if (currentTick >= maxTick){
            currentTick = 0;
            currentImageIndex++;
            if (currentImageIndex == images.length)
                currentImageIndex = 0;
        }
    }

    public BufferedImage getCurrentImage(){
        return images[currentImageIndex];
    }

}
