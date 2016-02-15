package me.volition.location.impl;

import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.placeableObject.impl.Building1;
import me.volition.location.tile.BrickWall_Side;
import me.volition.location.tile.BrickWall_Top;
import me.volition.location.tile.Grass;
import me.volition.location.tile.Street;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/14/2016.
 */
public class Midtown extends Location {
    public Midtown() {
        super("Midtown", true, true);
    }

    @Override
    public Tile[][] loadMap() {

        ArrayList<Class<? extends Tile>> tiles = new ArrayList<>();
        tiles.add(BrickWall_Top.class);
        tiles.add(BrickWall_Side.class);
        tiles.add(Street.class);
        tiles.add(Grass.class);

        ArrayList<Class<? extends PlaceableObject>> objects = new ArrayList<>();
        objects.add(Building1.class);

        Tile[][] tilemap = ImageManager.loadMapFromImage(this, ImageManager.getInstance().loadImage("/me/volition/assets/image/rooms/midtown.png"), tiles, objects);

        return tilemap;
    }

    @Override
    public void loadExits(Tile[][] tilemap) {
        addExit(new Exit(tilemap, 8 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, MarkApartment.class, 10 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true));
    }
}
