package me.volition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class Window {

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;

    private JFrame frame;

    public Window(JPanel jPanel){
        frame = new JFrame();
        frame.setTitle("The Adventures of Mark");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);

        frame.add(jPanel);
        frame.setVisible(true);
    }

    public void addKeyListener(KeyListener keyListener){
        frame.addKeyListener(keyListener);
    }

}
