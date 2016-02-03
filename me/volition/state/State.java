package me.volition.state;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public interface State {

    void init();

    void update();

    void render(Graphics g);

    void keyPressed(int k);

    void keyReleased(int k);

}
