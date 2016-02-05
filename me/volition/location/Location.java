package me.volition.location;

import me.volition.*;
import me.volition.Window;
import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.location.solidobject.SolidObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {
    private String name;
    private ArrayList<Entity> entities;
    private ArrayList<Exit> exits;
    private ArrayList<SolidObject> solidObjects;
    private BufferedImage backgroundImage;


    public Location(BufferedImage backgroundImage, String name) {
        this.backgroundImage = backgroundImage;
        entities = new ArrayList<>();
        exits = new ArrayList<>();
        solidObjects = new ArrayList<>();
        this.name = name;
    }

    public void update(Player player){
        //makes sure player isnt colliding with an object
        for (SolidObject s : solidObjects) {
            Rectangle rectangle = s.getBounds();
            if (rectangle.intersects(player.getBounds()) || rectangle.contains(player.getBounds())) {
                if (player.getX() <= rectangle.x + rectangle.width && player.getX() >= rectangle.getX())
                    player.setGoingLeft(false);

                else if (player.getX() + player.getWidth() <= rectangle.x + rectangle.width && player.getX() + player.getWidth() >= rectangle.getX())
                    player.setGoingRight(false);

                if (player.getY() <= rectangle.y + rectangle.height && player.getY() >= rectangle.getY())
                    player.setGoingUp(false);

                else if (player.getY() + player.getHeight() <= rectangle.y + rectangle.height && player.getY() + player.getHeight() >= rectangle.getY())
                    player.setGoingDown(false);
            }
        }

        for (Exit exit: exits) {
            if (exit.isActive() && exit.contains(player.getBounds())){
                player.setLocation(exit.getLeadsTo());
            }
        }

        for (Entity e: entities){
            if (player.getX() == e.getX() && player.getY() == e.getY()){
                //start a battle xd
            }
        }
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

    public void addExit(Exit exit){
        exits.add(exit);
    }

    public void setSolidObjects(ArrayList<SolidObject> solidObjects){
        this.solidObjects = solidObjects;
    }

    public void addSolidObject(SolidObject solidObject){
        solidObjects.add(solidObject);
    }

    public ArrayList<SolidObject> getSolidObjects(){
        return solidObjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }

    public void render(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);

        for (SolidObject s: solidObjects)
            s.render(g);

        for (Exit e: exits)
            g.drawRect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
    }
}