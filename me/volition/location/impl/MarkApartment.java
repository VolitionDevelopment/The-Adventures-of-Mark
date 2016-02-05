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
    }

    public String s(){
        return "hi";
    }
}
