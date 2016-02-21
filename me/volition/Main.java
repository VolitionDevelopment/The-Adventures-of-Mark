package me.volition;

import me.volition.state.StateManager;
import me.volition.util.RenderUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Where the current state is handled.
 * created by mccloskeybr on 2/3/16.
 */
public class Main extends JPanel{

    public static Main gameMain;

    private Window window;
    private long oldTime;

    public Main(){

        setBackground(Color.BLACK);
        window = new Window(this);
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                StateManager.getCurrentState().keyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                StateManager.getCurrentState().keyReleased(e.getKeyCode());
            }
        });

    }

    public static Main getInstance(){
        if (gameMain == null)
            gameMain = new Main();

        return gameMain;
    }

    public void loop(){

        float deltaTime = System.currentTimeMillis() - oldTime;
        oldTime = System.currentTimeMillis();

        if (deltaTime > 0.02f) //frame cap
            deltaTime = 0.02f;

        StateManager.getCurrentState().update(deltaTime);

        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = RenderUtils.alias(g);
        StateManager.getCurrentState().render(g2);
    }

}
