package me.volition.state.menu.ingamemenu;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public interface InGameMenu {

    void update();
    void select(int currentIndex);
    void keyPressed(int k);
    void render(Graphics g);

}
