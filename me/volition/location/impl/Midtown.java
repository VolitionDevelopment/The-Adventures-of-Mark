package me.volition.location.impl;

import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.mapObject.placeableObject.impl.building.*;
import me.volition.mapObject.placeableObject.impl.furniture.Fence_horiz;
import me.volition.mapObject.placeableObject.impl.furniture.Fence_vert;
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
    public void loadMap() {

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
        objects.add(Shop_1.class);
        objects.add(Building_Block_small.class);
        objects.add(Building_Block_large.class);
        objects.add(Building_L2.class);
        objects.add(Building_U1.class);
        objects.add(Building_L3.class);
        objects.add(Fence_horiz.class);
        objects.add(Fence_vert.class);

        ImageManager.loadMapFromImage(this, ImageManager.getInstance().loadImage("/me/volition/assets/image/rooms/midtown.png"), tiles, objects);
    }

    @Override
    public void loadExits(Tile[][] tilemap) {
        addExit(new Exit(
                6 * Tile.TILE_SIZE, 8 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE,
                MarkApartment.class, 11 * Tile.TILE_SIZE, 13 * Tile.TILE_SIZE, true
        ));

        addExit(new Exit(
                17 * Tile.TILE_SIZE, 17 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE,
                Shop_Restaurant.class, 8 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true
        ));

        addExit(new Exit(
                27 * Tile.TILE_SIZE, 17 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE,
                Shop_Clothing.class, 6 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true
        ));

        addExit(new Exit(
                16 * Tile.TILE_SIZE, 28 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE,
                Shop_Dollar.class, 6 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true
        ));
    }
}
