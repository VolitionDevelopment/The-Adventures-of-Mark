package volition.lvgen;

import volition.lvgen.comp.LvGen_Tile;
import volition.lvgen.util.LvGen_ObjectManager;

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

        currentID = LvGen_ObjectManager.DEFAULT_ID;

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
                e.getX() < map[0].length * LvGen_ObjectManager.getTileRenderScale() + window.getToolBar().getWidth() &&
                e.getY() < map.length * LvGen_ObjectManager.getTileRenderScale() + 32) {

            int x = (int) ((e.getX() - window.getToolBar().getWidth()) * LvGen_ObjectManager.getTileRenderScale());
            int y = (int) ((e.getY() * LvGen_ObjectManager.getTileRenderScale()) - 0.1);

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
                map[i][j].render(g, (int) (j * 64 * LvGen_ObjectManager.getTileRenderScale()), (int) (i * 64 * LvGen_ObjectManager.getTileRenderScale()));

        g.setColor(Color.BLACK);

        g.drawRect(0, 0, (int) (map[0].length * 64 / LvGen_ObjectManager.getTileRenderScale()), (int) (map.length * 64 * LvGen_ObjectManager.getTileRenderScale()));
        g.drawString("(O)", (int) (map[0].length * 64 * LvGen_ObjectManager.getTileRenderScale()), (int) (map.length * 64 * LvGen_ObjectManager.getTileRenderScale() + g.getFontMetrics().getHeight()));

        if (showGrid) {
            for (int i = 0; i <= map.length; i++)
                g.drawLine(0, (int) (i * 64 * LvGen_ObjectManager.getTileRenderScale()), (int) (map.length * 64 * LvGen_ObjectManager.getTileRenderScale()), (int) (i * 64 * LvGen_ObjectManager.getTileRenderScale()));

            for (int i = 0; i <= map[0].length; i++)
                g.drawLine((int) (i * 64 * LvGen_ObjectManager.getTileRenderScale()), 0, (int) (i * 64 * LvGen_ObjectManager.getTileRenderScale()), (int) (map.length * 64 * LvGen_ObjectManager.getTileRenderScale()));
        }

        if (showID) {
            for (int i = 0; i < map.length; i++)
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j].getTile_ID() != 0) {
                        g.setColor(Color.WHITE);
                        g.fillRect((int) (j * LvGen_ObjectManager.getTileRenderScale()), (int) (i * 64 * LvGen_ObjectManager.getTileRenderScale()), g.getFontMetrics().stringWidth("" + map[i][j].getTile_ID()), g.getFontMetrics().getHeight());
                        g.setColor(Color.BLACK);
                        g.drawString("" + map[i][j].getTile_ID(), (int) (j * 64 * LvGen_ObjectManager.getTileRenderScale()), (int) (i * 64 * LvGen_ObjectManager.getTileRenderScale() + g.getFontMetrics().getHeight() - 2));
                    }
                }
        }

        window.redraw();

    }

}
