package me.volition.state.menu.ingamemenu.battle;

import me.volition.mapObject.entity.Entity;
import me.volition.state.battle.BattleState;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.FontManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
//similar to menustate, except is overlayed on top of the game / battle state
public abstract class BattleMenu extends InGameMenu {

    private BattleMenu prevMenu, subMenu;
    private int x;
    private Entity selectedEntity; //only for movemenu/entitymenu

    private BattleState state;

    public BattleMenu(String[] options, BattleState state) {
        super(options);
        this.state = state;
        x = 20;
    }

    public BattleMenu(String[] options, BattleMenu prevMenu) {
        super(options);
        this.prevMenu = prevMenu;
        x = prevMenu.getX() + 70;
    }

    public Entity getSelectedEntity(){
        return selectedEntity;
    }

    public int getX(){
        return x;
    }

    public BattleMenu getPrevMenu(){
        return prevMenu;
    }

    public BattleState getBattleState(){
        if (state == null && prevMenu != null)
            return prevMenu.getBattleState();

        return state;
    }

    public void setSelectedEntity(Entity entity){
        this.selectedEntity = entity;
    }

    public void setSubMenu(BattleMenu subMenu){
        this.subMenu = subMenu;
    }

    @Override
    public void update(){
        if (subMenu == null) {
            super.update();
        } else
            subMenu.update();
    }

    @Override
    public void keyPressed(int k){
        if (subMenu == null) {
            super.keyPressed(k);

            if (k == KeyEvent.VK_S)
                setCurrentIndex(getCurrentIndex() + 1);
            else if (k == KeyEvent.VK_W)
                setCurrentIndex(getCurrentIndex() - 1);
            else if (k == KeyEvent.VK_D && getCurrentIndex() + 5 < getOptions().length)
                setCurrentIndex(getCurrentIndex() + 5);
            else if (k == KeyEvent.VK_A && getCurrentIndex() - 5 > -1)
                setCurrentIndex(getCurrentIndex() - 5);

        } else {
            subMenu.keyPressed(k);
        }
    }

    @Override
    public void render(Graphics2D g){

        g.setFont(FontManager.getSans(14));

        int y = 3 * me.volition.Window.WINDOW_HEIGHT / 4;
        for (int i = 0; i < getOptions().length; i++) {
            if (i == getCurrentIndex())
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);

            g.drawString(getOptions()[i], x, y);

            y += 30;
            if (y >= me.volition.Window.WINDOW_HEIGHT - 30) {
                y = 3 * me.volition.Window.WINDOW_HEIGHT / 4;
                x += 120;
            }
        }

        if (subMenu != null)
            subMenu.render(g);
    }

    @Override
    public abstract void select(int currentIndex);

}
