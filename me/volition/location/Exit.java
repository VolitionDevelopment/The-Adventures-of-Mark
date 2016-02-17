package me.volition.location;


import me.volition.entity.Player;
import me.volition.location.tile.Tile;

public class Exit {
    // Numerical codes
    private boolean active;
    private Class<? extends Location> m_leadsTo = null;
    private double x, y, newx, newy;
    private int width, height;

    public Exit(Tile[][] tilemap, double x, double y, int width, int height, Class<? extends Location> leadsTo, int newx, int newy, boolean active) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.newx = newx;
        this.newy = newy;

        m_leadsTo = leadsTo;
        this.active = active;

        tilemap[(int) y / Tile.TILE_SIZE][(int) x / Tile.TILE_SIZE].setExit(this);
    }

    public void enter(Player player){
        try {
            player.setLocation(m_leadsTo.newInstance());
        } catch (Exception e) { e.printStackTrace(); }

        player.setX(newx);
        player.setY(newy);

        player.getLocation().enterRoom();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}