package volition.lvgen;

import volition.lvgen.util.LvGen_FileManager;
import volition.lvgen.util.LvGen_TileManager;

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

    private int tileType;

    private boolean mapSwitch;

    public LvGen_Window(JPanel jPanel, int tileType){

        this.tileType = tileType;

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
            LvGen_FileManager.getInstance().save("volition/adv_of_mark/assets/maps/chunk/centraltown/LVGEN.txt", LvGen_Main.getInstance().getMap(), tileType);
        });
        toolBar.add(save);
        toolBar.addSeparator();

        JButton open = new JButton("Open");
        open.addActionListener(e -> {
            LvGen_Main.getInstance().setMap(LvGen_FileManager.getInstance().open("/Users/mccloskeybr/IdeaProjects/GIT/The-Adventures-of-Mark/volition/adv_of_mark/assets/maps/LVGEN.txt"));
        });
        toolBar.add(open);
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

        HashMap<Integer, BufferedImage> idMap = LvGen_TileManager.getInstance().getIdMap();

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
