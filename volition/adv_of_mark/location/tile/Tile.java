package volition.adv_of_mark.location.tile;

import volition.adv_of_mark.location.Exit;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.TileManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 3/15/16.
 */
public class Tile {

    public static final int TILE_SIZE = 64;

    private int id;
    private boolean isSolid;

    private Exit exit;
    private Entity npc;
    private PlaceableObject object;
    private ArrayList<Entity> battleEntities;

    private BufferedImage image;

    public Tile(int id) {
        this.id = id;
        TileManager.setProperties(this);
    }

    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public BufferedImage getImage(){
        return image;
    }

    public Exit getExit(){
        return exit;
    }

    public Entity getNpc(){
        return npc;
    }

    public PlaceableObject getObject(){
        return object;
    }

    public ArrayList<Entity> getBattleEntities(){
        return battleEntities;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public void setNpc(Entity npc){
        this.npc = npc;
    }

    public void setObject(PlaceableObject object) {
        this.object = object;
    }

    public void setBattleEntities(ArrayList<Entity> battleEntities) {
        this.battleEntities = battleEntities;
    }

}
