package volition.lvgen;

import volition.lvgen.comp.LvGen_Tile;
import volition.lvgen.util.LvGen_TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_Main extends JPanel{

    private static LvGen_Main instance;

    public static LvGen_Main getInstance(){
        if (instance == null)
            instance = new LvGen_Main(0);

        return instance;
    }

    private LvGen_Window window;
    private boolean mousePressed;

    private LvGen_Tile[][] map;

    private int tileType;
    private boolean showGrid;
    private boolean showID;

    private int currentID;

    public LvGen_Main(int tileType){

        // tiletype
        // 0 tilemap, 1 locationmap

        this.tileType = tileType;

        currentID = LvGen_TileManager.DEFAULT_ID;

        if (tileType == 0)
            map = new LvGen_Tile[15][15];
        else
            map = new LvGen_Tile[10][10];


        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                map[i][j] = new LvGen_Tile();

        showGrid = true;

        // window

        window = new LvGen_Window(this, tileType);

        window.addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                draw(e);
                mousePressed = true;
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        window.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (mousePressed) {
                    draw(e);
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {}

        });

    }

    public int getTileType(){
        return tileType;
    }

    public LvGen_Tile[][] getMap(){
        return map;
    }

    public void setCurrentID(int currentID){
        this.currentID = currentID;
    }

    public void setMap(LvGen_Tile[][] map){
        this.map = map;
    }

    public void showGrid(){
        showGrid = !showGrid;
        repaint();
    }

    public void showID(){
        showID = !showID;
        repaint();
    }

    public void draw(MouseEvent e){
        if (
                e.getX() < map[0].length * LvGen_TileManager.getTileRender_size() + 64 &&
                e.getY() < map.length * LvGen_TileManager.getTileRender_size() + 32
                ) {

            int x = (e.getX() - window.getToolBar().getWidth()) / LvGen_TileManager.getTileRender_size();
            int y = (int) ((e.getY() / LvGen_TileManager.getTileRender_size()) - 0.5);

            map[y][x].setID(currentID);

        }
    }

    public void erase(){
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                map[i][j] = new LvGen_Tile();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                map[i][j].render(g, j * LvGen_TileManager.getTileRender_size(), i * LvGen_TileManager.getTileRender_size());

        g.setColor(Color.BLACK);

        if (showGrid) {
            for (int i = 0; i <= map.length; i++)
                g.drawLine(0, i * LvGen_TileManager.getTileRender_size(), map.length * LvGen_TileManager.getTileRender_size(), i * LvGen_TileManager.getTileRender_size());

            for (int i = 0; i <= map[0].length; i++)
                g.drawLine(i * LvGen_TileManager.getTileRender_size(), 0, i * LvGen_TileManager.getTileRender_size(), map.length * LvGen_TileManager.getTileRender_size());
        }

        if (showID) {
            for (int i = 0; i < map.length; i++)
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j].getTile_ID() != 0) {
                        g.setColor(Color.WHITE);
                        g.fillRect(j * LvGen_TileManager.getTileRender_size(), i * LvGen_TileManager.getTileRender_size(), g.getFontMetrics().stringWidth("" + map[i][j].getTile_ID()), g.getFontMetrics().getHeight());
                        g.setColor(Color.BLACK);
                        g.drawString("" + map[i][j].getTile_ID(), j * LvGen_TileManager.getTileRender_size(), i * LvGen_TileManager.getTileRender_size() + g.getFontMetrics().getHeight() - 2);
                    }
                }
        }
        window.redraw();

    }

}
