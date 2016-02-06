package me.volition.location.impl;

import me.volition.Window;
import me.volition.location.Location;
import me.volition.location.solidobject.Bed;
import me.volition.location.solidobject.PizzaBox;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodTile;
import me.volition.util.ImageManager;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MarkApartment extends Location {

    public MarkApartment() {
        super("Mark's Apartment");
    }

    @Override
    public Tile[][] loadMap() {
        Tile[][] tileMap = new Tile[Window.WINDOW_HEIGHT / Tile.TILE_SIZE][Window.WINDOW_WIDTH / Tile.TILE_SIZE];

        //set background
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                tileMap[i][j] = new WoodTile(j, i);
            }
        }

        //add solid objects
        addSolidObject(new Bed(tileMap, 10, 3));
        addSolidObject(new PizzaBox(tileMap, 5, 7));
        addSolidObject(new PizzaBox(tileMap, 2, 9));

        return tileMap;
    }
}
