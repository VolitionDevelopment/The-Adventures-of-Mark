package me.volition.location;

import me.volition.Window;
import me.volition.mapObject.entity.Entity;
import me.volition.mapObject.entity.Player;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.state.StateManager;
import me.volition.state.menu.impl.LoadMenu;
import me.volition.util.BattleManager;
import me.volition.util.GameManager;
import me.volition.util.ImageManager;
import me.volition.util.ObjectManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {

    private String name;
    private ArrayList<Exit> exits;
    private ArrayList<PlaceableObject> placeableObjects;
    private Tile[][] tilemap;
    private BufferedImage bgImage;
    private double bg_x, bg_y;
    private boolean freeCamera, safeRoom;
    private ArrayList<Entity> npcs;


    public Location(String name, boolean safeRoom, boolean freeCamera) {
        exits = new ArrayList<>();
        placeableObjects = new ArrayList<>();
        npcs = new ArrayList<>();

        this.name = name;
        this.freeCamera = freeCamera;
        this.safeRoom = safeRoom; //if false, random tiles can cause battles

        loadMap();
        this.bgImage = ImageManager.makeImageFromMap(this);

    }

    public boolean hasFreeCamera(){
        return freeCamera;
    }

    public Tile[][] getTilemap(){
        return tilemap;
    }

    public ArrayList<PlaceableObject> getPlaceableObjects(){
        return placeableObjects;
    }

    public void addExit(Exit exit){
        exits.add(exit);

        int x = (int) (exit.getX() / Tile.TILE_SIZE);
        int y = (int) (exit.getY() / Tile.TILE_SIZE);

        tilemap[y][x].setExit(exit);
    }

    public void addPlaceableObject(PlaceableObject placeableObject){

        placeableObjects.add(placeableObject);

        double x = placeableObject.getX() / Tile.TILE_SIZE;
        double y = placeableObject.getY() / Tile.TILE_SIZE;

        BufferedImage image = placeableObject.getImage();

        if (image != null) {
            int width = image.getWidth() / Tile.TILE_SIZE;
            int height = image.getHeight() / Tile.TILE_SIZE;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (y + i < tilemap.length &&
                            x + j < tilemap[i].length &&
                            image.getRGB(j * Tile.TILE_SIZE + Tile.TILE_SIZE / 2, i * Tile.TILE_SIZE + Tile.TILE_SIZE / 2) != 16777215) { //makes sure transp. tiles arent solid

                        tilemap[(int) y + i][(int) x + j].setSolid(placeableObject.isSolid());
                        tilemap[(int) y + i][(int) x + j].setObject(placeableObject);
                    }
                }
            }
        }
    }

    public void addNpc(Entity npc){

        npcs.add(npc);

        double x = npc.getX() / Tile.TILE_SIZE;
        double y = npc.getY() / Tile.TILE_SIZE;

        tilemap[(int) y][(int) x].setSolid(true);
        tilemap[(int) y][(int) x].setNpc(npc);
    }

    public void setTilemap(Tile[][] tilemap){
        this.tilemap = tilemap;
    }

    public void update(double delta){

        Player player = GameManager.getInstance().getPlayer();

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
        if (playerTile.getBattleEntities() != null) {

            player.stopMoving();

            player.setAnimator(player.getBattleAnimator());
            BattleManager.startBattle(player, playerTile.getBattleEntities(), playerTile.getImage());

            playerTile.setBattleEntities(null);

        }

        //check if at exit
        Exit exit = playerTile.getExit();
        if (exit != null && exit.isActive()) {

            StateManager.setCurrentState(new LoadMenu());

            exit.enter(player);
        }

        //update entity animations
        for (Entity npc: npcs)
            npc.update(delta);

    }

    public void adjustCamera(double delta){

        if (freeCamera) {

            Player player = GameManager.getInstance().getPlayer();

            //move objects if the player is moving
            if (player.isGoingDown()) {

                bg_y -= delta * player.getBaseSpeed();

                for (Entity npc: npcs)
                    npc.setY((npc.getY() - (delta * player.getBaseSpeed())));

                for (Exit exit : exits)
                    exit.setY(exit.getY() - (delta * player.getBaseSpeed()));

            } else if (player.isGoingUp()) {

                bg_y += delta * player.getBaseSpeed();

                for (Entity npc: npcs)
                    npc.setY((npc.getY() + (delta * player.getBaseSpeed())));

                for (Exit exit : exits)
                    exit.setY(exit.getY() + (delta * player.getBaseSpeed()));
            }

            if (player.isGoingLeft()) {

                bg_x += delta * player.getBaseSpeed();

                for (Entity npc: npcs)
                    npc.setX((npc.getX() + (delta * player.getBaseSpeed())));

                for (Exit exit : exits)
                    exit.setX(exit.getX() + (delta * player.getBaseSpeed()));

            } else if (player.isGoingRight()) {

                bg_x -= delta * player.getBaseSpeed();

                for (Entity npc: npcs)
                    npc.setX((npc.getX() - (delta * player.getBaseSpeed())));

                for (Exit exit : exits)
                    exit.setX(exit.getX() - (delta * player.getBaseSpeed()));

            }
        }

    }

    public void enterRoom(){

        Player player = GameManager.getInstance().getPlayer();

        loadExits(tilemap);

        if (freeCamera) {

            bg_x = Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2);
            bg_y = Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2);

            for (Exit exit : exits) {
                exit.setX(exit.getX() + Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2));
                exit.setY(exit.getY() + Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2));
            }
        }

    }

    public void inspect(){

        Player player = GameManager.getInstance().getPlayer();

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

        Entity npc = inspectTile.getNpc();

        if (npc != null) {

            ObjectManager.onObjectEvent(npc);

        } else {

            PlaceableObject object = inspectTile.getObject();

            if (object != null) {
                inspectTile.getObject().onInspect();
                inspectTile.getObject().setOnInspect(ObjectEvent.NONE);
            }

        }

    }

    public abstract void loadMap();

    public abstract void loadExits(Tile[][] tilemap);

    public void render(Graphics2D g) {

        g.drawImage(bgImage, (int) bg_x, (int) bg_y, null);

        for (Entity npc: npcs)
            npc.render(g);

        g.setColor(Color.WHITE);
        for (Exit e: exits)
            if (e.getX() + e.getWidth() > 0 && e.getX() < Window.WINDOW_WIDTH && e.getY() + e.getHeight() > 0 && e.getY() < Window.WINDOW_HEIGHT)
                g.fillRect((int) e.getX(), (int) e.getY(), e.getWidth(), e.getHeight());

    }
}