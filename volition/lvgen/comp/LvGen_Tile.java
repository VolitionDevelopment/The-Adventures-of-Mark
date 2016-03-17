package volition.lvgen.comp;

import volition.lvgen.util.LvGen_ObjectManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_Tile {

    private int tile_id;
    private int obj_id;

    public LvGen_Tile(){
        tile_id = LvGen_ObjectManager.DEFAULT_ID;
    }

    public LvGen_Tile(int tile_id){
        this.tile_id = tile_id;
    }

    public LvGen_Tile(int tile_id, int obj_id){
        this.tile_id = tile_id;
        this.obj_id = obj_id;
    }


    public int getTile_ID(){
        return tile_id;
    }

    public int getObj_ID(){
        return obj_id;
    }

    public void setID(int id){
        if (id >= 50)
            obj_id = id;
        else
            tile_id = id;
    }

    public void render(Graphics g, int x, int y){

        BufferedImage mapImage;

        if (obj_id == 0)
            mapImage = LvGen_ObjectManager.getInstance().getTileIconImage(tile_id);
        else
            mapImage = LvGen_ObjectManager.getInstance().getObjMapImage(obj_id);

        g.drawImage(mapImage, x, y, (int) (mapImage.getWidth() / LvGen_ObjectManager.getTileRenderScale()), (int) (mapImage.getHeight() / LvGen_ObjectManager.getTileRenderScale()), null);

    }

}
