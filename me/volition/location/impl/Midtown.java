package me.volition.location.impl;

import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.placeableObject.impl.building.*;
import me.volition.location.tile.BrickWall;
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
        tiles.add(BrickWall.class);
        tiles.add(Street.class);
        tiles.add(Grass.class);

        ArrayList<Class<? extends PlaceableObject>> objects = new ArrayList<>();
        objects.add(Apartments.class);
        objects.add(Shed_normal.class);
        objects.add(Shed_tall.class);
        objects.add(Building_L1.class);
        objects.add(Building_Long1.class);

        Tile[][] tilemap = ImageManager.loadMapFromImage(this, ImageManager.getInstance().loadImage("/me/volition/assets/image/rooms/midtown.png"), tiles, objects);

        loadExits(tilemap);

        return tilemap;
    }

    @Override
    public void loadExits(Tile[][] tilemap) {
        addExit(new Exit(tilemap, 6 * Tile.TILE_SIZE, 8 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, MarkApartment.class, 10 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true));
    }
}
