package me.volition.location;


public class Exit {
    // Numerical codes
    private boolean active;
    private int x;
    private int y;
    private Location m_leadsTo = null;
    private String m_directionName;
    private String m_shortDirectionName;


    public Exit(int x, int y, Location leadsTo, boolean active) {
        this.x = x;
        this.y = y;

        m_leadsTo = leadsTo;
        this.active = active;
    }

    @Override
    public String toString() {
        return m_directionName;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}