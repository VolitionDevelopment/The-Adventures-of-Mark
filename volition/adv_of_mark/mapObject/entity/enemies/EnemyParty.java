package volition.adv_of_mark.mapObject.entity.enemies;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.util.LocationManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 4/12/16.
 */
public class EnemyParty {

    private ArrayList<Entity> members;
    private double x, y;
    private double renderx, rendery;

    public EnemyParty(double x, double y){
        members = new ArrayList<>();

        this.x = this.renderx = x;
        this.y = this.rendery = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getRenderx() {
        return renderx;
    }

    public double getRendery(){
        return rendery;
    }

    public ArrayList<Entity> getMembers(){
        return members;
    }

    public void addEnemy(){
        members.add(new Chili());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRenderx(double x) {
        this.renderx = x;
    }

    public void setRendery(double y) {
        this.rendery = y;
    }

    public void reset() {
        renderx = x;
        rendery = y;
    }

    public void update(double delta){
        members.get(0).update(delta);
    }

    public void startBattle(){
        members.get(0).getAnimator().reset();
    }

    public void render(Graphics g) {

        Location location = LocationManager.getCurrentLocation();
        BufferedImage image = members.get(0).getImage();

        g.drawImage(
                image,
                (int) location.getBg_horizOffset() + (location.getTilemap().length * Tile.TILE_SIZE / 2)
                        + (int) ((Tile.TILE_SIZE / 2) * (renderx / Tile.TILE_SIZE) - (Tile.TILE_SIZE / 2) * (rendery / Tile.TILE_SIZE))
                        - image.getWidth() / 2,
                (int) location.getBg_vertOffset()
                        + (int) ((Tile.TILE_SIZE / 4) * (renderx / Tile.TILE_SIZE) + (Tile.TILE_SIZE / 4) * (rendery / Tile.TILE_SIZE))
                        - image.getHeight() / 2 * ((image.getHeight() / Tile.TILE_SIZE) - 1)
                        - Tile.TILE_SIZE / 2,
                null
        );
    }

}
