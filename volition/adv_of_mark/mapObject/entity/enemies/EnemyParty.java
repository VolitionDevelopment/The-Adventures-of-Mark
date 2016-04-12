package volition.adv_of_mark.mapObject.entity.enemies;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.util.GameManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 4/12/16.
 */
public class EnemyParty {

    private ArrayList<Entity> members;
    private double x, y;

    public EnemyParty(double x, double y){
        members = new ArrayList<>();

        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void addEnemy(){
        members.add(new Fratkid());
    }

    public ArrayList<Entity> getMembers(){
        return members;
    }

    public void render(Graphics g) {

        Location location = GameManager.getInstance().getGameState().getCurrentLocation();
        BufferedImage image = members.get(0).getImage();

        g.drawImage(
                image,
                (int) location.getBg_horizOffset() + (location.getTilemap().length * Tile.TILE_SIZE / 2)
                        + (int) ((Tile.TILE_SIZE / 2) * (x / Tile.TILE_SIZE) - (Tile.TILE_SIZE / 2) * (y / Tile.TILE_SIZE))
                        - image.getWidth() / 2,
                (int) location.getBg_vertOffset()
                        + (int) ((Tile.TILE_SIZE / 4) * (x / Tile.TILE_SIZE) + (Tile.TILE_SIZE / 4) * (y / Tile.TILE_SIZE))
                        - image.getHeight() / 2 * ((image.getHeight() / Tile.TILE_SIZE) - 1)
                        - Tile.TILE_SIZE / 2,
                null
        );
    }

}
