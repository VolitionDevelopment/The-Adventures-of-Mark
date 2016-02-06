package me.volition.location.impl;

import me.volition.location.Location;
import me.volition.location.tile.Tile;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/4/2016.
 */
public class Street extends Location {
    public Street() {
        super("The Sidewalk");
    }

    @Override
    public Tile[][] loadMap() {
        return new Tile[0][];
    }
}