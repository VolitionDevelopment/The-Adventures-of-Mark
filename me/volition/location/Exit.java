package me.volition.location;


import me.volition.entity.Player;
import me.volition.location.tile.Tile;

import java.awt.*;

public class Exit {
    // Numerical codes
    private boolean active;
    private Location m_leadsTo = null;
    private String m_directionName;
    private String m_shortDirectionName;
    private Rectangle bounds;
    private double x, y, newx, newy;
    private int width, height;

    public Exit(double x, double y, int width, int height, Location leadsTo, int newx, int newy, boolean active) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.newx = newx;
        this.newy = newy;

        bounds = new Rectangle((int) x, (int) y, width, height);

        m_leadsTo = leadsTo;
        this.active = active;
    }

    public void enter(Player player){
        player.setLocation(m_leadsTo);
        player.setX(newx);
        player.setY(newy);

        player.getLocation().enterRoom(player);
    }

    @Override
    public String toString() {
        return m_directionName;
    }

    public void setX(double x) {
        this.x = x;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public void setY(double y) {
        this.y = y;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getNewx() {
        return newx;
    }

    public double getNewy() {
        return newy;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void setDirectionName(String dirname) {
        m_directionName = dirname;
    }

    public String getDirectionName() {
        return m_directionName;
    }

    public void setShortDirectionName(String shortName) {
        m_shortDirectionName = shortName;
    }

    public String getShortDirectionName() {
        return m_shortDirectionName;
    }

    public void setLeadsTo(Location leadsTo) {
        m_leadsTo = leadsTo;
    }

    public Location getLeadsTo() {
        return m_leadsTo;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public boolean contains(int x, int y){
        return bounds.contains(x, y);
    }

    public boolean contains(Rectangle rectangle){
        return bounds.contains(rectangle) || bounds.intersects(rectangle);
    }
}