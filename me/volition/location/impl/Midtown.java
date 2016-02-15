package me.volition.location.impl;

import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.tile.BrickWall;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodTile;
import me.volition.util.ImageManager;

import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/14/2016.
 */
public class Midtown extends Location {
    public Midtown() {
        super("Midtown", false, true);
    }

    @Override
    public Tile[][] loadMap() {

        ArrayList<Class<? extends Tile>> tiles = new ArrayList<>();
        tiles.add(WoodTile.class);
        tiles.add(BrickWall.class);
        Tile[][] tilemap = ImageManager.loadMapFromImage(ImageManager.getInstance().loadImage("/me/volition/assets/image/rooms/midtown.png"), tiles, null);

        return tilemap;
    }

    @Override
    public void loadExits(Tile[][] tilemap) {
        addExit(new Exit(tilemap, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, new MarkApartment(), 10 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true));
    }
}
