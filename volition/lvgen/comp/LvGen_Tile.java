package volition.lvgen.comp;

import volition.lvgen.util.LvGen_TileManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_Tile {

    private int tile_id;

    public LvGen_Tile(){
        tile_id = LvGen_TileManager.DEFAULT_ID;
    }

    public LvGen_Tile(int tile_id){
        this.tile_id = tile_id;
    }

    public int getTile_ID(){
        return tile_id;
    }

    public void setID(int id){
        tile_id = id;
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(LvGen_TileManager.getInstance().getImage(tile_id), x, y, LvGen_TileManager.getTileRender_size(), LvGen_TileManager.getTileRender_size(), null);
    }

}
