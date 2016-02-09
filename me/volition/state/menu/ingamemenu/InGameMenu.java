package me.volition.state.menu.ingamemenu;

import me.volition.state.battle.BattleState;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
//similar to menustate, except is overlayed on top of the game / battle state
public abstract class InGameMenu {

    private int currentIndex;
    private boolean hasSelected;
    private String[] options;

    private BattleState state;

    public InGameMenu(String[] options, BattleState state) {
        this.options = options;
        this.state = state;
    }


    public BattleState getBattleState(){
        return state;
    }

    public void setBattleState(BattleState state){
        this.state = state;
    }

    public void update(){
        if (currentIndex < 0)
            currentIndex = options.length - 1;
        else if (currentIndex == options.length)
            currentIndex = 0;

        if (hasSelected)
            select(currentIndex);
    }

    public void keyPressed(int k){
        if (k == KeyEvent.VK_S)
            currentIndex++;
        else if (k == KeyEvent.VK_W)
            currentIndex--;
        else if (k == KeyEvent.VK_ENTER)
            hasSelected = true;
    }

    public void render(Graphics g){

    }

    public abstract void select(int currentIndex);

}
