package me.volition.state.menu.ingamemenu.battle;

import me.volition.entity.Entity;
import me.volition.state.battle.BattleState;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.RenderUtils;

import java.awt.*;

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
        } else {
            subMenu.keyPressed(k);
        }
    }

    @Override
    public void render(Graphics g){
        Graphics2D g2 = RenderUtils.alias(g);
        g2.setFont(new Font("Determination Sans", Font.PLAIN, 14));
        for (int i = 0; i < getOptions().length; i++) {
            if (i == getCurrentIndex())
                g2.setColor(Color.RED);
            else
                g2.setColor(Color.WHITE);

            g2.drawString(getOptions()[i], x, 3 * me.volition.Window.WINDOW_HEIGHT / 4 + 30 * i);
        }

        if (subMenu != null)
            subMenu.render(g);
    }

    @Override
    public abstract void select(int currentIndex);

}
