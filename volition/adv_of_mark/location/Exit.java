package volition.adv_of_mark.location;


import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.util.GameManager;

public class Exit {
    // Numerical codes
    private boolean active;
    private Class<? extends Location> m_leadsTo = null;
    private double x, y, newx, newy;
    private int width, height;

    public Exit(double x, double y, int width, int height, Class<? extends Location> leadsTo, int newx, int newy, boolean active) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.newx = newx;
        this.newy = newy;

        m_leadsTo = leadsTo;
        this.active = active;
    }

    public void enter(Player player){
        try {
            GameManager.getInstance().getGameState().setLocation(m_leadsTo.newInstance());
        } catch (Exception e) { e.printStackTrace(); }

        player.setX(newx);
        player.setY(newy);

        GameManager.getInstance().getGameState().getCurrentLocation().enterRoom();
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