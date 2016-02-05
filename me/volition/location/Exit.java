package me.volition.location;


import java.awt.*;

public class Exit {
    // Numerical codes
    private boolean active;
    private Location m_leadsTo = null;
    private String m_directionName;
    private String m_shortDirectionName;
    private Rectangle bounds;


    public Exit(Rectangle bounds, Location leadsTo, boolean active) {
        this.bounds = bounds;

        m_leadsTo = leadsTo;
        this.active = active;
    }

    @Override
    public String toString() {
        return m_directionName;
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