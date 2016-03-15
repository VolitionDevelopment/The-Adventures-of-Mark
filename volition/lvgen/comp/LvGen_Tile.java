package volition.lvgen.comp;

import volition.lvgen.util.LvGen_TileManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_Tile {

    private int id;

    public LvGen_Tile(){
        id = LvGen_TileManager.DEFAULT_ID;
    }

    public LvGen_Tile(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public void render(Graphics g, int x, int y){

        g.drawImage(LvGen_TileManager.getInstance().getImage(id), x, y, LvGen_TileManager.getTileRender_size(), LvGen_TileManager.getTileRender_size(), null);

    }

}
