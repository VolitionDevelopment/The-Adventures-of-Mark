package me.volition.state.menu.ingamemenu.battle;

import me.volition.entity.Entity;
import me.volition.state.battle.BattleState;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
//similar to menustate, except is overlayed on top of the game / battle state
public abstract class BattleMenu implements InGameMenu {

    private int currentIndex;
    private boolean hasSelected;
    private String[] options;
    private BattleMenu prevMenu, subMenu;
    private int x;
    private Entity selectedEntity; //only for movemenu/entitymenu

    private BattleState state;

    public BattleMenu(String[] options, BattleState state) {
        this.options = options;
        this.state = state;
        x = 20;
    }

    public BattleMenu(String[] options, BattleMenu prevMenu) {
        this.options = options;
        this.prevMenu = prevMenu;
        x = prevMenu.getX() + 70;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public void setSelectedEntity(Entity entity){
        this.selectedEntity = entity;
    }

    public Entity getSelectedEntity(){
        return selectedEntity;
    }

    public int getX(){
        return x;
    }

    public void setPrevMenu(BattleMenu prevMenu){
        this.prevMenu = prevMenu;
    }

    public void setSubMenu(BattleMenu subMenu){
        this.subMenu = subMenu;
    }

    public BattleMenu getPrevMenu(){
        return prevMenu;
    }

    public BattleMenu getSubMenu(){
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public abstract void select(int currentIndex);

}
