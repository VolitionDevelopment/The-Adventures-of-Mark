package me.volition.location;

import me.volition.Window;
import me.volition.entity.Player;
import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.state.menu.ingamemenu.game.DialogueMenu;
import me.volition.util.BattleManager;
import me.volition.util.GameManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {
    private String name;
    private ArrayList<Exit> exits;
    private ArrayList<PlaceableObject> placeableObjects;
    private Tile[][] tilemap;
    private boolean freeCamera, safeRoom;


    public Location(String name, boolean safeRoom, boolean freeCamera) {
        exits = new ArrayList<>();
        placeableObjects = new ArrayList<>();
        this.name = name;
        this.freeCamera = freeCamera;
        this.safeRoom = safeRoom; //if false, random tiles can cause battles
        tilemap = loadMap();
    }

    public void update(Player player, double delta){

        //collision detection
        int distConst = 10;

        if (tilemap[(int) (player.getY() + distConst) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid() ||
                tilemap[(int) (player.getY() + player.getHeight() - distConst) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid())
            player.setGoingRight(false);

        else if (tilemap[(int) (player.getY() + distConst) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid() ||
                tilemap[((int) player.getY() + player.getHeight() - distConst) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid())
            player.setGoingLeft(false);

        if (tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][(int) (player.getX() + distConst) / Tile.TILE_SIZE].isSolid() ||
                tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() - distConst) / Tile.TILE_SIZE].isSolid())
            player.setGoingDown(false);

        else if (tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) (player.getX() + distConst) / Tile.TILE_SIZE].isSolid() ||
                tilemap[(int) player.getY() / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() - distConst) / Tile.TILE_SIZE].isSolid())
            player.setGoingUp(false);

        Tile playerTile = tilemap[((int) player.getY() + player.getHeight() / 2) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() / 2) / Tile.TILE_SIZE];

        //random battles
        if (!safeRoom && player.isMoving()){
            if (Math.random() < Math.pow(delta, 2.5)) {

                player.stopMoving();

                player.setAnimator(player.getBattleAnimator());
                BattleManager.startBattle(player, playerTile.getImage());

            }
        }

        //battle tiles
        if (playerTile.getEntities() != null) {

            player.stopMoving();

            player.setAnimator(player.getBattleAnimator());
            BattleManager.startBattle(player, playerTile.getEntities(), playerTile.getImage());

            playerTile.setEntities(null);

        }

        //check if at exit
        Exit exit = playerTile.getExit();
        if (exit != null && exit.isActive())
            exit.enter(player);

    }

    public void adjustCamera(double delta, Player player){
        if (freeCamera) {
            //move objects if the player is moving
            if (player.isGoingDown()) {

                for (Tile[] aTilemap : tilemap)
                    for (Tile anATilemap : aTilemap)
                        anATilemap.setY(anATilemap.getY() - (delta * player.getBaseSpeed()));

                for (PlaceableObject placeableObject : placeableObjects)
                    placeableObject.setY(placeableObject.getY() - (delta * player.getBaseSpeed()));

                for (Exit exit : exits)
                    exit.setY(exit.getY() - (delta * player.getBaseSpeed()));

            } else if (player.isGoingUp()) {

                for (Tile[] aTilemap : tilemap)
                    for (Tile anATilemap : aTilemap)
                        anATilemap.setY(anATilemap.getY() + (delta * player.getBaseSpeed()));

                for (PlaceableObject placeableObject : placeableObjects)
                    placeableObject.setY(placeableObject.getY() + (delta * player.getBaseSpeed()));

                for (Exit exit : exits)
                    exit.setY(exit.getY() + (delta * player.getBaseSpeed()));
            }

            if (player.isGoingLeft()) {

                for (Tile[] aTilemap : tilemap)
                    for (Tile anATilemap : aTilemap)
                        anATilemap.setX(anATilemap.getX() + (delta * player.getBaseSpeed()));

                for (PlaceableObject placeableObject : placeableObjects)
                    placeableObject.setX(placeableObject.getX() + (delta * player.getBaseSpeed()));

                for (Exit exit : exits)
                    exit.setX(exit.getX() + (delta * player.getBaseSpeed()));

            } else if (player.isGoingRight()) {

                for (Tile[] aTilemap : tilemap)
                    for (Tile anATilemap : aTilemap)
                        anATilemap.setX(anATilemap.getX() - (delta * player.getBaseSpeed()));

                for (PlaceableObject placeableObject : placeableObjects)
                    placeableObject.setX(placeableObject.getX() - (delta * player.getBaseSpeed()));

                for (Exit exit : exits)
                    exit.setX(exit.getX() - (delta * player.getBaseSpeed()));

            }
        }
    }

    public void enterRoom(Player player){
        loadExits(tilemap);

        if (freeCamera) {
            for (Tile[] aTilemap : tilemap) {
                for (Tile tile : aTilemap) {
                    tile.setX(tile.getX() + Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2));
                    tile.setY(tile.getY() + Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2));
                }
            }

            for (PlaceableObject placeableObject : placeableObjects) {
                placeableObject.setX(placeableObject.getX() + Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2));
                placeableObject.setY(placeableObject.getY() + Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2));
            }

            for (Exit exit : exits) {
                exit.setX(exit.getX() + Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2));
                exit.setY(exit.getY() + Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2));
            }
        }

    }

    public void inspect(Player player){
        int playerx = (int) (player.getX() + player.getWidth() / 2) / Tile.TILE_SIZE;
        int playery = (int) (player.getY() + player.getHeight() / 2) / Tile.TILE_SIZE;

        Tile inspectTile;

        if (player.isFacingUp())
            inspectTile = tilemap[playery - 1][playerx];

        else if (player.isFacingDown())
            inspectTile = tilemap[playery + 1][playerx];

        else if (player.isFacingRight())
            inspectTile = tilemap[playery][playerx + 1];

        else
            inspectTile = tilemap[playery][playerx - 1];

        PlaceableObject object = inspectTile.getObject();
        if (object != null) {
            GameManager.getInstance().getGameState().setInGameMenu(new DialogueMenu(player, object.getName() + " - " + object.getDesc()));

            inspectTile.getObject().onInspect(player);
            inspectTile.getObject().setEvent(ObjectEvent.NONE);
        }
    }

    public boolean hasFreeCamera(){
        return freeCamera;
    }

    public void addExit(Exit exit){
        exits.add(exit);
    }

    public void addPlaceableObject(PlaceableObject placeableObject){
        placeableObjects.add(placeableObject);
    }

    public abstract Tile[][] loadMap();

    public abstract void loadExits(Tile[][] tilemap);

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }

    public void render(Graphics g) {
        for (Tile[] array: tilemap){
            for (Tile tile: array)
                if (tile.getX() + Tile.TILE_SIZE > 0 && tile.getX() < Window.WINDOW_WIDTH && tile.getY() + Tile.TILE_SIZE > 0 && tile.getY() < Window.WINDOW_HEIGHT)
                    tile.render(g);
        }

        for (PlaceableObject s: placeableObjects)
            if (s.getX() + s.getWidth() > 0 && s.getX() < Window.WINDOW_WIDTH && s.getY() + s.getHeight() > 0 && s.getY() < Window.WINDOW_HEIGHT)
                s.render(g);

        g.setColor(Color.WHITE);
        for (Exit e: exits)
            if (e.getX() + e.getWidth() > 0 && e.getX() < Window.WINDOW_WIDTH && e.getY() + e.getHeight() > 0 && e.getY() < Window.WINDOW_HEIGHT)
                g.fillRect((int) e.getX(), (int) e.getY(), e.getWidth(), e.getHeight());
    }
}