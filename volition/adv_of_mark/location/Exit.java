package volition.adv_of_mark.location;


import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.LocationManager;

public class Exit {
    // Numerical codes
    private int newLocX, newLocY;
    private int x, y, newx, newy;

    public Exit(int x, int y, int newLocX, int newLocY, int newx, int newy) {
        this.x = x;
        this.y = y;
        this.newx = newx;
        this.newy = newy;

        this.newLocX = newLocX;
        this.newLocY = newLocY;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}