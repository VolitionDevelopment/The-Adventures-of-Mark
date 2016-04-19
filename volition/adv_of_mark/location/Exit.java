package volition.adv_of_mark.location;


import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.LocationManager;

public class Exit {
    // Numerical codes
    private boolean active;
    private int newLocX, newLocY;
    private double x, y, newx, newy;
    private int width, height;

    public Exit(double x, double y, int width, int height, int newLocX, int newLocY, int newx, int newy, boolean active) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.newx = newx;
        this.newy = newy;

        this.newLocX = newLocX;
        this.newLocY = newLocY;
        this.active = active;
    }

    public void enter(Player player){
        LocationManager.setLocation(newLocX, newLocY);

        player.setX(newx);
        player.setY(newy);

        LocationManager.getCurrentLocation().enterRoom();
    }

    public Location getLeadsTo(){
        return LocationManager.getLocationFromMap(newLocX, newLocY);
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