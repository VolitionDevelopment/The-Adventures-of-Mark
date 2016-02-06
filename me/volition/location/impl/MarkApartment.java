package me.volition.location.impl;

import me.volition.Window;
import me.volition.location.Location;
import me.volition.location.placeableObject.Bed;
import me.volition.location.placeableObject.Desk;
import me.volition.location.placeableObject.PizzaBox;
import me.volition.location.placeableObject.TexasCarpet;
import me.volition.location.tile.BrickWall;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodTile;

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
                if (i == 0 || j == 0 || i == tileMap.length - 1 || j == tileMap[i].length - 1)
                    tileMap[i][j] = new BrickWall(j, i);
                else
                    tileMap[i][j] = new WoodTile(j, i);
            }
        }

        //add solid objects
        addPlaceableObject(new Bed(tileMap, 8, 2));
        addPlaceableObject(new Desk(tileMap, 2, 1));
        addPlaceableObject(new PizzaBox(tileMap, 1, 2));
        addPlaceableObject(new PizzaBox(tileMap,  2, 5));
        addPlaceableObject(new PizzaBox(tileMap, 4, 4));
        addPlaceableObject(new TexasCarpet(tileMap, 5, 3));

        return tileMap;
    }
}
