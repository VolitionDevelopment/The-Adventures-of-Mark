package me.volition.location;

import me.volition.*;
import me.volition.Window;
import me.volition.entity.Entity;
import me.volition.entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {
    private String name;
    private ArrayList<Entity> entities;
    private ArrayList<Exit> exits;
    private BufferedImage backgroundImage;


    public Location(BufferedImage backgroundImage, String name) {
        this.backgroundImage = backgroundImage;
        entities = new ArrayList<>();
        this.name = name;
        exits = new ArrayList<>();
    }

    public void update(Player player){
        //makes sure player is in bounds
        if (player.getX() <= 0)
            player.setGoingLeft(false);
        else if (player.getX() + player.getWidth() >= Window.WINDOW_WIDTH)
            player.setGoingRight(false);
        if (player.getY() <= 0)
            player.setGoingUp(false);
        else if (player.getY() + player.getHeight() >= Window.WINDOW_HEIGHT)
            player.setGoingDown(false);

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
        for (Exit e: exits) {
            g.drawRect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
        }
    }
}