package me.volition.state;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public abstract class State {
    private int state;

    public State(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);

}
