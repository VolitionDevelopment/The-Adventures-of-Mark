package me.volition.location.impl;

import me.volition.location.Location;
import me.volition.location.tile.BrickWall;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodFloor;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;
import me.volition.util.ObjectManager;

import java.util.ArrayList;

/**
 * Created by mccloskeybr on 3/2/16.
 */
public class ApartmentRoom extends Location {

    public ApartmentRoom() {
        super("Apartment Room", false);
    }

    @Override
    public void loadMap() {

        ArrayList<Class<? extends Tile>> tiles = new ArrayList<>();

        tiles.add(BrickWall.class);
        tiles.add(WoodFloor.class);
        tiles.add(WoodFloor.class);

        ArrayList<Class<? extends PlaceableObject>> objects = new ArrayList<>();

        try {

            objects.add(ObjectManager.getRandomObject(1));
            objects.add(ObjectManager.getRandomObject(1));
            objects.add(ObjectManager.getRandomObject(1));
            objects.add(ObjectManager.getRandomObject(1));
            objects.add(ObjectManager.getRandomObject(1));

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageManager.loadMapFromImage(this, ImageManager.getInstance().loadImage("/me/volition/assets/image/rooms/apartment_room.png"), tiles, objects);

    }

    @Override
    public void loadExits(Tile[][] tilemap) {

    }
}
