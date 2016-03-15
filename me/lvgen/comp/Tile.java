package me.lvgen.comp;

import me.lvgen.util.TileManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class Tile {

    private int id;

    public Tile(){
        id = TileManager.DEFAULT_ID;
    }

    public Tile(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public void render(Graphics g, int x, int y){

        g.drawImage(TileManager.getInstance().getImage(id), x, y, TileManager.getTileRender_size(), TileManager.getTileRender_size(), null);

    }

}
