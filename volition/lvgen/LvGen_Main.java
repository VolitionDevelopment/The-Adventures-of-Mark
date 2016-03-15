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
            instance = new LvGen_Main();

        return instance;
    }

    private LvGen_Window window;
    private boolean mousePressed;

    private LvGen_Tile[][] map;

    private boolean showGrid;
    private boolean showID;

    private int currentID;

    public LvGen_Main(){

        currentID = LvGen_TileManager.DEFAULT_ID;

        map = new LvGen_Tile[15][15];
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                map[i][j] = new LvGen_Tile();

        showGrid = true;

        // window

        window = new LvGen_Window(this);

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
                e.getX() < map[0].length * LvGen_TileManager.getTileRender_size() + window.getToolBar().getWidth() &&
                e.getY() < map.length * LvGen_TileManager.getTileRender_size() + 32) {

            int x = (e.getX() - window.getToolBar().getWidth()) / LvGen_TileManager.getTileRender_size();
            int y = (int) ((e.getY() / LvGen_TileManager.getTileRender_size()) - 0.1);

            if (x >= 0 && y >= 0)
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

        g.drawRect(0, 0, map[0].length * LvGen_TileManager.getTileRender_size(), map.length * LvGen_TileManager.getTileRender_size());
        g.drawString("(O)", map[0].length * LvGen_TileManager.getTileRender_size(), map.length * LvGen_TileManager.getTileRender_size() + g.getFontMetrics().getHeight());

        if (showGrid) {
            for (int i = 0; i <= map.length; i++)
                g.drawLine(0, i * LvGen_TileManager.getTileRender_size(), map.length * LvGen_TileManager.getTileRender_size(), i * LvGen_TileManager.getTileRender_size());

            for (int i = 0; i <= map[0].length; i++)
                g.drawLine(i * LvGen_TileManager.getTileRender_size(), 0, i * LvGen_TileManager.getTileRender_size(), map.length * LvGen_TileManager.getTileRender_size());
        }

        if (showID) {
            for (int i = 0; i < map.length; i++)
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j].getID() != 0) {
                        g.setColor(Color.WHITE);
                        g.fillRect(j * LvGen_TileManager.getTileRender_size(), i * LvGen_TileManager.getTileRender_size(), g.getFontMetrics().stringWidth("" + map[i][j].getID()), g.getFontMetrics().getHeight());
                        g.setColor(Color.BLACK);
                        g.drawString("" + map[i][j].getID(), j * LvGen_TileManager.getTileRender_size(), i * LvGen_TileManager.getTileRender_size() + g.getFontMetrics().getHeight() - 2);
                    }
                }
        }

        window.redraw();

    }

}
