package me.volition;

import me.volition.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Where the current state is handled.
 * created by mccloskeybr on 2/3/16.
 */
public class Main extends JPanel{

    private Window window;

    private long oldTime;

    public Main(){

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

        //noinspection InfiniteLoopStatement
        while (true)
            loop();
    }

    public void loop(){
        float deltaTime = System.currentTimeMillis() - oldTime;
        oldTime = System.currentTimeMillis();

        if (deltaTime > 0.02f)
            deltaTime = 0.02f;

        StateManager.getCurrentState().update(deltaTime);

        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        StateManager.getCurrentState().render(g);
    }

}
