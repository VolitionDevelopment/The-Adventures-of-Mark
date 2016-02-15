package me.volition.location.impl;

import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.placeableObject.*;
import me.volition.location.placeableObject.impl.*;
import me.volition.location.tile.BrickWall_Side;
import me.volition.location.tile.BrickWall_Top;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodTile;
import me.volition.util.ImageManager;

import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MarkApartment extends Location {

    public MarkApartment() {
        super("Mark's Apartment", true, false);
    }

    @Override
    public Tile[][] loadMap() {

        ArrayList<Class<? extends Tile>> tiles = new ArrayList<>();
        tiles.add(BrickWall_Top.class);
        tiles.add(BrickWall_Side.class);
        tiles.add(WoodTile.class);
        tiles.add(WoodTile.class);

        ArrayList<Class<? extends PlaceableObject>> objects = new ArrayList<>();
        objects.add(TexasCarpet.class);
        objects.add(PizzaBox.class);
        objects.add(Television.class);
        objects.add(Chest.class);
        objects.add(Couch.class);
        objects.add(Desk.class);
        objects.add(Bed.class);
        objects.add(Toilet.class);
        objects.add(LavaLamp.class);
        objects.add(Wardrobe.class);

        Tile[][] tileMap = ImageManager.loadMapFromImage(this, ImageManager.getInstance().loadImage("/me/volition/assets/image/rooms/marksroom.png"), tiles, objects);

        addPlaceableObject(new PlaceableBattleTile(tileMap, null, 3 * Tile.TILE_SIZE, 3 * Tile.TILE_SIZE));

        return tileMap;
    }

    @Override
    public void loadExits(Tile[][] tileMap){
        addExit(new Exit(tileMap, tileMap[0].length * Tile.TILE_SIZE - Tile.TILE_SIZE * 2, tileMap.length / 2 * Tile.TILE_SIZE + 2 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, Midtown.class, Tile.TILE_SIZE * 2, 2 * Tile.TILE_SIZE, true));
    }
}
