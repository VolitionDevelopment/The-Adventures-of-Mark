package me.volition.location.impl;

import me.volition.location.Location;
import me.volition.util.ImageManager;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MarkApartment extends Location {

    public MarkApartment() {
        super(new ImageManager().loadImage("/me/volition/assets/image/rooms/marksapt.png"), "Mark's Apartment");
        addSolidObject(new Rectangle(100, 225, 160, 80));
        addSolidObject(new Rectangle(120, 360, 100, 100));
        addSolidObject(new Rectangle(450, 125, 160, 110));
    }

    public String s(){
        return "hi";
    }
}
