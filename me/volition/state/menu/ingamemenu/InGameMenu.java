package me.volition.state.menu.ingamemenu;

import me.volition.*;
import me.volition.state.battle.BattleState;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
//similar to menustate, except is overlayed on top of the game / battle state
public abstract class InGameMenu {

    private int currentIndex;
    private boolean hasSelected;
    private String[] options;
    private InGameMenu prevMenu, subMenu;
    private int x;

    private BattleState state;

    public InGameMenu(String[] options, BattleState state) {
        this.options = options;
        this.state = state;
        x = 20;
    }

    public InGameMenu(String[] options, InGameMenu prevMenu) {
        this.options = options;
        this.prevMenu = prevMenu;
        x = prevMenu.getX() + 70;
    }

    public int getX(){
        return x;
    }

    public void setPrevMenu(InGameMenu prevMenu){
        this.prevMenu = prevMenu;
    }

    public void setSubMenu(InGameMenu subMenu){
        this.subMenu = subMenu;
    }

    public InGameMenu getPrevMenu(){
        return prevMenu;
    }

    public InGameMenu getSubMenu(){
        return subMenu;
    }

    public BattleState getBattleState(){
        if (state == null && prevMenu != null)
            return prevMenu.getBattleState();
        
        return state;
    }

    public void setBattleState(BattleState state){
        this.state = state;
    }

    public void update(){
        if (subMenu == null) {
            if (currentIndex < 0)
                currentIndex = options.length - 1;
            else if (currentIndex >= options.length)
                currentIndex = 0;

            if (hasSelected) {
                select(currentIndex);
                hasSelected = false;
            }
        } else
            subMenu.update();
    }

    public void keyPressed(int k){
        if (subMenu == null) {
            if (k == KeyEvent.VK_S)
                currentIndex++;
            else if (k == KeyEvent.VK_W)
                currentIndex--;
            else if (k == KeyEvent.VK_ENTER)
                hasSelected = true;
        } else {
            subMenu.keyPressed(k);
        }
    }

    public void render(Graphics g){
        Graphics2D g2 = RenderUtils.alias(g);
        g2.setFont(new Font("Determination Sans", Font.PLAIN, 14));
        for (int i = 0; i < options.length; i++) {
            if (i == currentIndex)
                g2.setColor(Color.RED);
            else
                g2.setColor(Color.WHITE);

            g2.drawString(options[i], x, 3 * me.volition.Window.WINDOW_HEIGHT / 4 + 30 * i);
        }

        if (subMenu != null)
            subMenu.render(g);
    }

    public abstract void select(int currentIndex);

}
