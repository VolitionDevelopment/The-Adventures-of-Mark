package me.volition.state.menu.ingamemenu;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public abstract class InGameMenu {

    private int currentIndex;
    private String[] options;

    public InGameMenu(String[] options){
        this.options = options;
    }

    public String[] getOptions(){
        return options;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public void setOptions(String[] options){
        this.options = options;
    }

    public void setCurrentIndex(int currentIndex){
        this.currentIndex = currentIndex;
    }

    public void update(){
        if (currentIndex < 0)
            currentIndex = options.length - 1;
        else if (currentIndex >= options.length)
            currentIndex = 0;
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE)
            select(currentIndex);
        else if (k == KeyEvent.VK_ESCAPE)
            select(0);
    }

    public abstract void select(int currentIndex);

    public abstract void render(Graphics2D g);

}
