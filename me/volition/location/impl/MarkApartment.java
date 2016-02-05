package me.volition.location.impl;

import me.volition.location.Location;
import me.volition.location.solidobject.Bed;
import me.volition.location.solidobject.PizzaBox;
import me.volition.util.ImageManager;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MarkApartment extends Location {

    public MarkApartment() {
        super(new ImageManager().loadImage("/me/volition/assets/image/rooms/marksapt.png"), "Mark's Apartment");
        addSolidObject(new Bed(500, 125));
        addSolidObject(new PizzaBox(300, 400));
        addSolidObject(new PizzaBox(100, 300));
    }

    public String s(){
        return "hi";
    }
}
