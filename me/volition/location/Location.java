package me.volition.location;

import me.volition.entity.Entity;

import java.awt.*;

import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Location {

    private ArrayList<Entity> entities;

    public Location(String name, int x, int y) {
        entities = new ArrayList<>();
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

    public abstract void render(Graphics g);
}