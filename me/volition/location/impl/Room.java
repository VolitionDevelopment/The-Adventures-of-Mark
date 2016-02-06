package me.volition.location.impl;

import me.volition.location.Location;
import me.volition.location.tile.Tile;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Room extends Location {
    public Room() {
        super("Room");
    }

    @Override
    public Tile[][] loadMap() {
        return new Tile[0][];
    }

}
