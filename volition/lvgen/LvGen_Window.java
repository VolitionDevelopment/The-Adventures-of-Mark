package volition.lvgen;

import volition.lvgen.util.LvGen_FileManager;
import volition.lvgen.util.LvGen_ObjectManager;
import volition.lvgen.util.LvGen_ObjectProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_Window {

    public static final int WINDOW_WIDTH = 832;
    public static final int WINDOW_HEIGHT = 640;

    private JFrame frame;
    private JToolBar toolBar;

    private boolean mapSwitch;

    public LvGen_Window(JPanel jPanel){

        frame = new JFrame();
        frame.setTitle("LvGen");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT + 23);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createToolbar();
        createTileSelectMenu();

        frame.add(jPanel);
        frame.setVisible(true);

    }

    private void createToolbar(){

        toolBar = new JToolBar();

        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            LvGen_FileManager.getInstance().save("LVGEN_lv.txt", LvGen_Main.getInstance().getMap());
        });
        toolBar.add(save);
        toolBar.addSeparator();

        JButton open = new JButton("Open");
        open.addActionListener(e -> {
            LvGen_Main.getInstance().setMap(LvGen_FileManager.getInstance().open("/Users/mccloskeybr/IdeaProjects/GIT/The-Adventures-of-Mark/LVGEN_lv.txt"));
        });
        toolBar.add(open);
        toolBar.addSeparator();

        JButton switchObj = new JButton("Tile:Object");
        switchObj.addActionListener(e -> {

            mapSwitch = !mapSwitch;

            if (mapSwitch)
                createTileSelectMenu();
            else
                createObjectSelectMenu();

        });
        toolBar.add(switchObj);
        toolBar.addSeparator();

        JButton showGrid = new JButton("Show Grid");
        showGrid.addActionListener(e -> {
            LvGen_Main.getInstance().showGrid();
        });
        toolBar.add(showGrid);
        toolBar.addSeparator();

        JButton showID = new JButton("Show ID");
        showID.addActionListener(e -> {
            LvGen_Main.getInstance().showID();
        });
        toolBar.add(showID);
        toolBar.addSeparator();

        JButton erase = new JButton("Erase");
        erase.addActionListener(e -> {
            LvGen_Main.getInstance().erase();
        });
        toolBar.add(erase);

        toolBar.setOrientation(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        frame.getContentPane().add(toolBar, BorderLayout.WEST);

    }

    private void createTileSelectMenu(){

        HashMap<Integer, BufferedImage> idMap = LvGen_ObjectManager.getInstance().getIdMap();

        int tileMenu_tileSize = 64;
        int tileMenu_X = LvGen_Window.WINDOW_WIDTH - tileMenu_tileSize * 4 - 1;

        int x = 0, y = 0;
        for (int i = 0; i < idMap.keySet().size(); i++) {

            int flag = i;

            JButton button = new JButton(new ImageIcon(idMap.get(flag)));

            button.setBounds(tileMenu_X + x, y, tileMenu_tileSize, tileMenu_tileSize);
            button.addActionListener(e -> LvGen_Main.getInstance().setCurrentID(flag));
            button.setVisible(true);
            frame.add(button);

            x += tileMenu_tileSize;
            if (x == 4 * tileMenu_tileSize) {
                x = 0;
                y += tileMenu_tileSize;
            }
        }

    }

    private void createObjectSelectMenu(){

        HashMap<Integer, LvGen_ObjectProperty> objMap = LvGen_ObjectManager.getInstance().getObjectMap();

        int tileMenu_tileSize = 64;
        int tileMenu_X = LvGen_Window.WINDOW_WIDTH - tileMenu_tileSize * 4 - 1;

        int x = 0, y = 0;
        for (int i = 50; i < 50 + objMap.keySet().size(); i++) {

            int flag = i;

            JButton button = new JButton(new ImageIcon(objMap.get(flag).getIconImage()));

            button.setBounds(tileMenu_X + x, y, tileMenu_tileSize, tileMenu_tileSize);
            button.addActionListener(e -> LvGen_Main.getInstance().setCurrentID(flag));
            button.setVisible(true);
            frame.add(button);

            x += tileMenu_tileSize;
            if (x == 4 * tileMenu_tileSize) {
                x = 0;
                y += tileMenu_tileSize;
            }
        }
    }

    public JToolBar getToolBar(){
        return toolBar;
    }

    public void addMouseListener(MouseListener mouseListener){
        frame.addMouseListener(mouseListener);
    }

    public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
        frame.addMouseMotionListener(mouseMotionListener);
    }

    public void redraw(){
        frame.repaint();
    }

}
