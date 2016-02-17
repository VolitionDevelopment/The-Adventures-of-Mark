package me.volition.location.impl;

import me.volition.entity.Player;
import me.volition.entity.shopkeepers.Peppito;
import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodFloor;
import me.volition.location.tile.WoodWall;
import me.volition.state.menu.ingamemenu.game.RestaurantShopMenu;
import me.volition.util.ImageManager;

import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/17/16.
 */
public class Shop_Restaurant extends Location {
    public Shop_Restaurant() {
        super("Peppito's Pizzaria", true, false);
    }

    @Override
    public void loadMap() {

        ArrayList<Class<? extends Tile>> tiles = new ArrayList<>();
        tiles.add(WoodWall.class);
        tiles.add(WoodFloor.class);
        tiles.add(WoodFloor.class);

        ArrayList<Class<? extends PlaceableObject>> objects = new ArrayList<>();

        ImageManager.loadMapFromImage(this, ImageManager.getInstance().loadImage("/me/volition/assets/image/rooms/shop_restaurant.png"), tiles, objects);

        addNpc(new Peppito(this, 5 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE));

    }

    @Override
    public void loadExits(Tile[][] tilemap) {
        addExit(new Exit(
                tilemap,
                8 * Tile.TILE_SIZE, 8 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE,
                Midtown.class, 17 * Tile.TILE_SIZE, 18 * Tile.TILE_SIZE, true
        ));
    }

    @Override
    public void enterRoom(){
        super.enterRoom();

        RestaurantShopMenu.generateRandomItems();
    }

}
