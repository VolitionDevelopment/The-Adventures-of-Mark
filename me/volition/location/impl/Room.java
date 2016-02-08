package me.volition.location.impl;

import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.tile.BrickWall;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodTile;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Room extends Location {
    public Room() {
        super("Room", true);
    }

    @Override
    public Tile[][] loadMap() {
        Tile[][] tileMap = new Tile[6][20];
        //set background
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                if (i == 0 || j == 0 || i == tileMap.length - 1 || j == tileMap[i].length - 1)
                    tileMap[i][j] = new BrickWall(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
                else
                    tileMap[i][j] = new WoodTile(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
            }
        }

        return tileMap;
    }

}
