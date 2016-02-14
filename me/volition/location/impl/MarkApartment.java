package me.volition.location.impl;

import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.placeableObject.*;
import me.volition.location.tile.BrickWall;
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
        tiles.add(WoodTile.class);
        tiles.add(BrickWall.class);

        Tile[][] tileMap = ImageManager.loadMapFromImage(new ImageManager().loadImage("/me/volition/assets/image/rooms/marksroom.png"), tiles, null);

        //add solid objects
        addPlaceableObject(new TexasCarpet(tileMap, 3 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE));
        addPlaceableObject(new PizzaBox(tileMap,  3 * Tile.TILE_SIZE, 6 * Tile.TILE_SIZE));
        addPlaceableObject(new PizzaBox(tileMap, 5 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE));
        addPlaceableObject(new Television(tileMap, 4 * Tile.TILE_SIZE, 8 * Tile.TILE_SIZE));
        addPlaceableObject(new Wardrobe(tileMap, 10 * Tile.TILE_SIZE, Tile.TILE_SIZE));
        addPlaceableObject(new LavaLamp(tileMap, 9 * Tile.TILE_SIZE, Tile.TILE_SIZE));
        addPlaceableObject(new Bed(tileMap, 8 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE));
        addPlaceableObject(new Toilet(tileMap, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE));
        addPlaceableObject(new Desk(tileMap, Tile.TILE_SIZE, 5 * Tile.TILE_SIZE));
        addPlaceableObject(new Couch(tileMap, 4 * Tile.TILE_SIZE, 6 * Tile.TILE_SIZE));

        addPlaceableObject(new PlaceableBattleTile(tileMap, null, 3 * Tile.TILE_SIZE, 3 * Tile.TILE_SIZE));

        return tileMap;
    }

    @Override
    public void loadExits(Tile[][] tileMap){
        addExit(new Exit(tileMap[0].length * Tile.TILE_SIZE - Tile.TILE_SIZE * 2, tileMap.length / 2 * Tile.TILE_SIZE + 2 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, new Room(), Tile.TILE_SIZE * 2, 2 * Tile.TILE_SIZE, true));
    }
}
